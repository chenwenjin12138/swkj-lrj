package service.impl;
import	java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import pojo.*;
import pojo.order.CustomHouseService;
import pojo.order.Order;
import pojo.order.OrderMonthCard;
import pojo.order.OrderWashing;
import pojo.user.AppStaff;
import pojo.user.AppUser;
import service.IOrderService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;
import util.JavaSmsApi;
import vo.BalanceVo;
import vo.ItemJson;
import vo.OrderInfo;
import vo.Page;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static common.Constant.NOT_DELETED;
import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

import static pojo.CardAndItemCat.COLUMN_CARD_ID;
import static pojo.Reservation.*;
import static pojo.order.Order.*;
import static pojo.order.OrderMonthCard.COLUMN_ORDER_NUMBER;
import static pojo.user.AppUser.COLUMN_APP_USER_ID;

/**
 * @author fl
 * @descrip: 订单管理实现类
 * @date 2020/5/11 0011下午 4:42
 */
@Service
public class OrderServiceImpl implements IOrderService {

    public static final String API_KEY = "cf71f38465ba87627035f066ad456e4a";

    @Resource
    private IOrderMapper iOrderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private StaffMapper staffMapper;

    @Resource
    private BalanceVoMapper balanceVoMapper;

    @Resource
    private OrderWashingMapper orderWashingMapper;

    @Resource
    private ReservationMapper reservationMapper;

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    @Resource
    private ConsigneeMapper consigneeMapper;

    @Resource
    private TraceStatusNameMapper traceStatusNameMapper;

    @Resource
    private OrderHouseServiceMapper orderHouseServiceMapper;

    @Resource
    private OrderMonthCardMapper orderMonthCardMapper;

    @Resource
    private IMonthCardMapper monthCardMapper;

    @Resource
    private IItemMapper itemMapper;

    @Resource
    private IItemCatMapper itemCatMapper;

    @Resource
    private ICardAndItemCatMapper cardAndItemCatMapper;

    @Resource
    private CustomHouseServiceMapper customHouseServiceMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    public OrderServiceImpl(IOrderMapper iOrderMapper) {
        this.iOrderMapper = iOrderMapper;
    }

    @Override
    public PageInfo<Order> getOrderPageByParam(RequestDTO requestDTO) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        Order order = objectMapper.convertValue(requestDTO.getData(), Order.class);
        queryWrapper.eq(DELETE_COLUMN, NOT_DELETED);
        if (order != null && StringUtils.isNotEmpty(order.getOrderNumber())) {
            queryWrapper.like(ORDER_NUMBER_COLUMN, order.getOrderNumber());
        }
     /*   if (order != null && StringUtils.isNotEmpty(order.getRechargeAccount())) {
            queryWrapper.like(RECHARGE_ACCOUNT_COLUMN, order.getRechargeAccount());
        }*/
        if (StringUtils.isNotEmpty(requestDTO.getBeginTime()) && StringUtils.isNotEmpty(requestDTO.getEndTime())) {
            queryWrapper.between(CREATE_TIME_COLUMN, requestDTO.getBeginTime(), requestDTO.getEndTime());
        }
        if (order != null && order.getOrderType() != 0 ) {
            queryWrapper.eq(ORDER_TYPE_COLUMN, order.getOrderType());
        }
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        PageHelper.startPage(requestDTO.getPage(), requestDTO.getSize());
        List<Order> list = iOrderMapper.selectList(queryWrapper);
        return new PageInfo<Order>(list);
    }

    @Override
    public ReturnData<Boolean> updateOrder(Order order) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<Order>();
        updateWrapper.eq(COLUMN_APP_USER_ID, order.getId());
        if (iOrderMapper.update(order, updateWrapper) > 0) {
            return new ReturnData(SUCCESS_CODE, "操作成功", true);
        }
        return new ReturnData(Fail_CODE, "操作失败", false);
    }


    @Override
    public ReturnData<Boolean> addOrder(Order order) {
        return null;
    }

    //TODO 充值订单退款流程
    @Override
    public ReturnData<Boolean> refund(Order order) {
        return null;
    }

    /**
     * @param: requestDTO
     * @Description: Order信息分页查询
     * @Author: LxH
     * @Date: 2020/5/15 21:37
     */
    @Override
    public Page<OrderInfo> getAppOrderInfoPageByParam(OrderInfo orderInfo, RequestDTO requestDTO) {

        String startTime = null;
        String endTime = null;
        if (orderInfo.getInquire() == null) {
            startTime = DateUtils.getStartTime();
            endTime = DateUtils.getEndTime();
            requestDTO.setBeginTime(startTime).setEndTime(endTime);
            return getAppOrderInfo(requestDTO, orderInfo);
        }
           /*2020-05-18 00:00:00
            2020-05-19 23:59:59*/
        String inquireTime = orderInfo.getInquireTime();
        String startTimeNew = inquireTime + " 01:00:00";
        String endTimeNew = inquireTime + " 23:59:59";
        requestDTO.setBeginTime(startTimeNew).setEndTime(endTimeNew);
        return getAppOrderInfo(requestDTO, orderInfo);
    }

    /**
     * @param: orderNumbers
     * @param: phoneNumbers
     * @Description: 短信群发功能
     * @Author: LxH
     * @Date: 2020/5/20 17:09
     */
    @Override
    public ReturnData sendMessages(String[] orderNumbers, String[] phoneNumbers) {
        String context = "【懒人家】尊敬的懒人家用户，您好：您的订单编号为#orderNumber#的物件在分拣过程中发现：#content#，其他：（#other#）如情况不属实请于10分钟内致电客服0871-65628435，超出时间将视作情况属实，如出现问题将按照《用户须知》进行处理。";
        for (int i = 0; i < orderNumbers.length; i++) {
            Example example = new Example(SmsTemplate.class);
            example.createCriteria().andEqualTo("orderNumber", orderNumbers[i]);
            List<SmsTemplate> smsTemplates = smsTemplateMapper.selectByExample(example);
            for (SmsTemplate smsTemplate : smsTemplates) {
                if (smsTemplate.getOther() == null || "".equals(smsTemplate.getOther())) {
                    smsTemplate.setOther("无");
                }
                /*问题内容*/
                StringBuilder content = new StringBuilder();
                /*破损问题*/
                if (smsTemplate.getIsWearOut() == 1) {
                    content.append("[破损问题]");
                }
                /* 染色问退 */
                if (smsTemplate.getIsDye() == 1) {
                    content.append("[染色问题]");
                }
                /* 起球问退 */
                if (smsTemplate.getIsBallingUp() == 1) {
                    content.append("[起球问题]");
                }
                /* 发黄发白问题 */
                if (smsTemplate.getIsYellowWhite() == 1) {
                    content.append("[发黄发白问题]");
                }
                /* 串色问题 */
                if (smsTemplate.getIsCrossColor() == 1) {
                    content.append("[串色问题]");
                }
                /* 替换内容 */
                context = context.replace("#orderNumber#", orderNumbers[i]).replace("#content#", content.toString()).replace("#other#", smsTemplate.getOther());
                try {
                    String s = JavaSmsApi.sendSms(API_KEY, context, phoneNumbers[i]);
                    System.out.println("--------------------" + s + "-------------------------");
                    smsTemplate.setUpdateTime(DateUtils.formatDate(new Date()));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return new ReturnData(SUCCESS_CODE, "短信发送成功", true);
    }

    /**
     * @param orderInfo
     * @Description: 获取订单商品信息
     * @Author: LxH
     * @Date: 2020/5/29 11:25
     */
    @Override
    public ReturnData findOrderItem(OrderInfo orderInfo) {
        return null;
    }

    private Page<OrderInfo> getAppOrderInfo(RequestDTO requestDTO, OrderInfo orderInfo){
        ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
        Example example = new Example(Reservation.class);
        example.orderBy(Reservation.COLUMN_CREATE_TIME);
        Example.Criteria criteria = example.createCriteria();
        if (orderInfo.getFindType()==1) {
            criteria.andBetween(COLUMN_ORDER_TYPE,TYPE_LAUNDRY,TYPE_HOUSEKEEPING);
            criteria.andNotEqualTo(COLUMN_ORDER_TYPE,TYPE_MONTH_CARD);
        } else if (orderInfo.getFindType()==2){
            criteria.andBetween(COLUMN_ORDER_TYPE,TYPE_MONTH_CARD,TYPE_CUSTOM);
            criteria.andNotEqualTo(COLUMN_ORDER_TYPE,TYPE_HOUSEKEEPING);
        }
        criteria.andBetween(Reservation.COLUMN_CREATE_TIME,requestDTO.getBeginTime(),requestDTO.getEndTime());
        Integer size = requestDTO.getSize();
        int start = requestDTO.getPage() * requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start, size);
        List<Reservation> reservations = reservationMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (Reservation reservation : reservations) {
            OrderInfo info = new OrderInfo();
            if (reservation.getIsShare()==null) {
                info.setIsShare(reservation.getIsShare());
                info.setShareName("未分享");
            }else if (reservation.getIsShare()==1){
                info.setIsShare(reservation.getIsShare());
                info.setShareName("已分享");
            }
            /*if (reservation.getPayStatus()==1) {
                info.setPayStatus(reservation.getPayStatus());
                info.setPayStatusName("已支付");
            }else if (reservation.getPayStatus()==0){
                info.setPayStatus(reservation.getPayStatus());
                info.setPayStatusName("未支付");
            }*/
            if (reservation.getIsEnd()==null||reservation.getIsEnd()==0) {
                info.setIsEnd(reservation.getIsEnd());
                info.setIsEndName("订单未完成");
            }else if (reservation.getIsEnd()==1){
                info.setIsEnd(reservation.getIsEnd());
                info.setIsEndName("订单已完成");
            }
            String number = reservation.getOrderNumber();
            Integer userId = reservation.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            info.setOrderNumber(number).setUserId(userId).setUnderOrderTime(reservation.getCreateTime()).setUserPhone(user.getUserPhone())
                    .setNickname(user.getNickName()).setTotalPrice(reservation.getTotalPrice()).setType(reservation.getOrderType());
            if (reservation.getOrderType().equals(TYPE_LAUNDRY) || reservation.getOrderType().equals(TYPE_HOUSEKEEPING)) {
                if (reservation.getTakeOrderTime()==null) {
                    info.setTakeOrderTime("未抢单");
                }else {
                    info.setTakeOrderTime(reservation.getTakeOrderTime());
                }
                if (reservation.getGrabOrderId()!=null) {
                    AppStaff appStaff = staffMapper.selectByPrimaryKey(reservation.getGrabOrderId());
                    info.setAppStaffId(appStaff.getAppStaffId()).setStaffUser(appStaff.getStaffUser()).setRealName(appStaff.getRealName())
                            .setTelephone(appStaff.getTelephone()).setRegisterTime(appStaff.getRegisterTime());
                }
                TraceStatusName traceStatusName = traceStatusNameMapper.selectByPrimaryKey(reservation.getTrackingStatus());
                info.setTraceStatusName(traceStatusName.getTraceStatusName());
                if (reservation.getIsUrgent() == null||reservation.getIsUrgent() == 0) {
                    info.setUrgentFee(new BigDecimal("0.00"));
                } else if (reservation.getIsUrgent() == 1){
                    info.setUrgentFee(new BigDecimal("50.00"));
                }
                if (reservation.getIsService() == null||reservation.getIsService() == 0) {
                    info.setServiceCharge(new BigDecimal("0.00"));
                }else if (reservation.getIsService() == 1){
                    info.setServiceCharge(new BigDecimal("8.00"));
                }
                if (reservation.getStatus()==1) {
                    info.setStatus(reservation.getStatus());
                    info.setStatusName("已抢");
                }else if (reservation.getStatus()==0){
                    info.setStatus(reservation.getStatus());
                    info.setStatusName("未抢");
                }
                if (reservation.getOrderType().equals(TYPE_LAUNDRY)) {
                    if (reservation.getReGetClothesTime()==null) {
                        info.setReGetClothesTime("未取衣");
                    }else {
                        info.setReGetClothesTime(reservation.getReGetClothesTime());
                    }
                    if (reservation.getSendBackTime()==null) {
                        info.setSendBackTime("未送回");
                    }else {
                        info.setSendBackTime(reservation.getSendBackTime());
                    }
                    info = getLaundry(number,info);
                }
                if (reservation.getOrderType().equals(TYPE_HOUSEKEEPING)) {
                    if (reservation.getReGetClothesTime()==null) {
                        info.setReGetClothesTime("未到达");
                    }else {
                        info.setReGetClothesTime(reservation.getReGetClothesTime());
                    }
                    if (reservation.getSendBackTime()==null) {
                        info.setSendBackTime("未结束");
                    }else {
                        info.setSendBackTime(reservation.getSendBackTime());
                    }
                    info = getHousekeeping(number,info);
                }
            }
            if (reservation.getOrderType().equals(TYPE_MONTH_CARD) || reservation.getOrderType().equals(TYPE_CUSTOM)) {
                if (reservation.getOrderType().equals(TYPE_MONTH_CARD)) {
                    info = findMonthCard(number,info);
                }
                if (reservation.getOrderType().equals(TYPE_CUSTOM)) {
                    info = findCustom(number,info);
                }
            }
            list.add(info);
        }
        if (orderInfo.getInquire()==null) {
            return new Page<OrderInfo>(list,list.size());
        }else if (orderInfo.getInquire()==1){
            return conditionalQuery(list,orderInfo);
        }
        return null;
    }

    private Page<OrderInfo> conditionalQuery(ArrayList<OrderInfo> list, OrderInfo orderInfo) {
        return null;
    }

    private OrderInfo findCustom(String number, OrderInfo info) {
        Example example = new Example(CustomHouseService.class);
        example.createCriteria().andNotEqualTo(COLUMN_ORDER_NUMBER,number);
        List<CustomHouseService> customHouseServices = customHouseServiceMapper.selectByExample(example);
        for (CustomHouseService customHouseService : customHouseServices) {
            List<AppItem> appItems = JSON.parseArray(customHouseService.getIndividualServiceJson(), AppItem.class);
            customHouseService.setItemList(appItems);
            info.setCustomHouseService(customHouseService).setTotalPrice(customHouseService.getBaseServicePrice());
        }
        return info;
    }

    private OrderInfo findMonthCard(String number, OrderInfo info) {
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> num = new ArrayList<Integer>();
        MonthCard monthCard = new MonthCard();
        Example example = new Example(OrderMonthCard.class);
        example.createCriteria().andNotEqualTo(COLUMN_ORDER_NUMBER,number);
        List<OrderMonthCard> orderMonthCards = orderMonthCardMapper.selectByExample(example);
        for (OrderMonthCard orderMonthCard : orderMonthCards) {
            info.setUserMonthCardCount(orderMonthCard.getUserMonthCardCount()).setStartTime(orderMonthCard.getCreateTime()).setEndTime(orderMonthCard.getEndTime());
            monthCard = monthCardMapper.selectByPrimaryKey(orderMonthCard.getMonthCardId());
        }
        Example e = new Example(CardAndItemCat.class);
        e.createCriteria().andEqualTo(COLUMN_CARD_ID,monthCard.getCardId());
        List<CardAndItemCat> cardAndItemCats = cardAndItemCatMapper.selectByExample(e);
       /* for (CardAndItemCat cardAndItemCat : cardAndItemCats) {
            *//*AppItemCat appItemCat = itemCatMapper.selectByPrimaryKey(cardAndItemCat.getAppItemCategoryId());
            name.add(appItemCat.getCategoryName());
            num.add(cardAndItemCat.getCategoryNum());*//*
        }
        monthCard.setAppItemCategoryName(name).setCategoryNum(num);
        info.setMonthCard(monthCard).setTotalPrice(monthCard.getPrice());*/
        return info;
    }

    private OrderInfo getHousekeeping(String number, OrderInfo info) {
        Example example = new Example(OrderHouseService.class);
        example.createCriteria().andNotEqualTo("orderNumber",number);
        List<OrderHouseService> orderHouseServices = orderHouseServiceMapper.selectByExample(example);
        for (OrderHouseService orderHouseService : orderHouseServices) {
            Consignee consignee = consigneeMapper.selectByPrimaryKey(orderHouseService.getTakeConsigneeId());
            info.setTakeUserName(consignee.getConsigneeName()).setTakePhone(consignee.getConsigneeMobile()).setTakeConsignee(consignee.getName()+consignee.getAddress());
            info.setHouseItem(itemMapper.selectByPrimaryKey(orderHouseService.getItemId()));
        }
        return info;
    }

    private OrderInfo getLaundry(String number, OrderInfo info) {
        Example example = new Example(OrderWashing.class);
        example.createCriteria().andNotEqualTo("orderNumber",number);
        List<OrderWashing> orderWashings = orderWashingMapper.selectByExample(example);
        for (OrderWashing orderWashing : orderWashings) {
            info.setGetClothesTime(orderWashing.getTakeTime());
            Consignee takeConsignee = consigneeMapper.selectByPrimaryKey(orderWashing.getTakeConsigneeId());
            info.setTakeUserName(takeConsignee.getConsigneeName()).setTakePhone(takeConsignee.getConsigneeMobile()).setTakeConsignee(takeConsignee.getName()+takeConsignee.getAddress());
            Consignee sendConsignee = consigneeMapper.selectByPrimaryKey(orderWashing.getSendConsigneeId());
            info.setSendUserName(sendConsignee.getConsigneeName()).setSendPhone(sendConsignee.getConsigneeMobile()).setSendConsignee(sendConsignee.getName()+sendConsignee.getAddress());
            info.setListItems(JSON.parseArray(orderWashing.getShoppingJson(), ItemJson.class));
            BalanceVo balanceVo = balanceVoMapper.selectByPrimaryKey(info.getUserId());
            if (balanceVo==null) {
                info.setUseBalance(new BigDecimal("0.00"));
            }else {
                info.setUseBalance(balanceVo.getBalance());
            }
        }
        return info;
    }

   /* private Page<OrderInfo> getAppOrderInf(RequestDTO requestDTO, OrderInfo orderInfo) {
        ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
        Example example = new Example(Order.class);
        example.orderBy("createTime");
        Example.Criteria criteria = example.createCriteria();
        if (orderInfo.getOrderNumber()!=null) {
            criteria.andEqualTo("orderNumber",orderInfo.getOrderNumber());
        }
        if (orderInfo.getType()!=null) {
            criteria.andEqualTo("orderType",orderInfo.getType());
        }
        criteria.andEqualTo("status", 0).andBetween("createTime", requestDTO.getBeginTime(), requestDTO.getEndTime());
        Integer size = requestDTO.getSize();
        if (orderInfo.getUserPhone()!=null) {
            size = iOrderMapper.selectCount(new Order());
        }
        int start = requestDTO.getPage() * requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start, size);
        List<Order> orders = iOrderMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (Order order : orders) {
            OrderInfo info = new OrderInfo();
            info.setIsShare(order.getIsShare()).setOrderNumber(order.getOrderNumber()).setPayStatus(order.getPayStatus()).setUnderOrderTime(order.getCreateTime()).setType(order.getOrderType()).setTotalPrice(order.getTotalPrice());
            String orderNumber = order.getOrderNumber();
            BigInteger orderId = new BigInteger(orderNumber);
            Integer userId = order.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            *//*try {
                Distributionrelation distributionrelation = distributionrelationMapper.selectByPrimaryKey(userId);
                User u = userMapper.selectByPrimaryKey(distributionrelation.getSuperId());
                info.setSuperUser(u.getNickName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (info.getSuperUser() == null) {
                    info.setSuperUser("无");
                }
            }*//*
            info.setUserId(userId).setNickname(user.getNickName()).setUserPhone(user.getUserPhone());
            Example e = new Example(AppStaffOrder.class);
            e.createCriteria().andEqualTo("orderId", orderId);
            List<AppStaffOrder> appStaffOrders = appStaffOrderMapper.selectByExample(e);
            Example example1 = new Example(Reservation.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("orderNumber", order.getOrderNumber());
            List<Reservation> reservations = reservationMapper.selectByExample(example1);
            for (Reservation reservation : reservations) {
                if (reservation.getTrackingStatus() == 11) {
                    info.setSentTime(reservation.getUpdateTime());
                }
            }
            *//*Example example2 = new Example(SmsTemplate.class);
            example.createCriteria().andEqualTo("orderNumber", order.getOrderNumber());
            List<SmsTemplate> smsTemplates = smsTemplateMapper.selectByExample(example2);
            for (SmsTemplate smsTemplate : smsTemplates) {
                info.setItemDefects(smsTemplate.getOther());
            }*//*
            for (AppStaffOrder appStaffOrder : appStaffOrders) {
                info.setTakeOrderTime(appStaffOrder.getCreateTime()).setAppStaffId(appStaffOrder.getStaffId());
                AppStaff appStaff = staffMapper.selectByPrimaryKey(appStaffOrder.getStaffId());
                info.setTelephone(appStaff.getTelephone()).setRealName(appStaff.getRealName()).setRegisterTime(appStaff.getRegisterTime()).setStaffUser(appStaff.getStaffUser()).setStaffPassword(appStaff.getStaffPassword());
            }
            list.add(info);
        }
        if (list.size() == 0 || list == null) {
            return new Page<OrderInfo>(list, list.size());
        } else {
            String number = orderInfo.getOrderNumber();
            String userPhone = orderInfo.getUserPhone();
            Integer type = orderInfo.getType();
            String inquireTime = orderInfo.getInquireTime();
            *//*2020-05-18 00:00:00
            2020-05-19 23:59:59*//*
            ArrayList<OrderInfo> infos = new ArrayList<OrderInfo>();

            for (OrderInfo reOrderInfo : list) {
                if (number != null && userPhone != null) {
                    if (number.equals(reOrderInfo.getOrderNumber()) && userPhone.equals(reOrderInfo.getUserPhone())) {
                        infos.add(reOrderInfo);
                    }
                }
                if (userPhone != null) {
                    if (userPhone.equals(reOrderInfo.getUserPhone())) {
                        infos.add(reOrderInfo);
                    }
                }
                if (type != null) {
                    if (type.equals(reOrderInfo.getType())) {
                        infos.add(reOrderInfo);
                    }
                }
                if (inquireTime != null) {
                    if (inquireTime.equals(reOrderInfo.getUnderOrderTime().substring(0, 10))) {
                        infos.add(reOrderInfo);
                    }
                }
            }
            if (infos.size()==0||infos==null) {
                return new Page<OrderInfo>(list, list.size());
            }
            return new Page<OrderInfo>(infos, infos.size());
        } *//*else {
                return new Page<OrderInfo>(list, list.size());
            }*//*
    }*/

    @Override
    public List<Order> getAppOrderListByParam(RequestDTO requestDTO) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        Order order = null;
        try {
            order = objectMapper.convertValue(requestDTO.getData(), Order.class);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (order != null && StringUtils.isNotEmpty(order.getUserId().toString())) {
            queryWrapper.like(USER_ID_COLUMN, order.getUserId());
        }
        queryWrapper.orderByDesc(AppUser.COLUMN_CREATE_TIME);
        return iOrderMapper.selectList(queryWrapper);
    }
}

