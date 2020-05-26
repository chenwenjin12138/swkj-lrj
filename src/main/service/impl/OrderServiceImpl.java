package service.impl;
import	java.io.IOException;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import pojo.*;
import pojo.order.Order;
import pojo.user.AppStaff;
import service.IOrderService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;
import util.JavaSmsApi;
import vo.OrderInfo;
import vo.Page;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static common.Constant.NOT_DELETED;
import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

import static pojo.order.Order.*;
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
    private AppStaffOrderMapper appStaffOrderMapper;

    @Resource
    private DistributionrelationMapper distributionrelationMapper;

    @Resource
    private ReservationMapper reservationMapper;

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    public OrderServiceImpl(IOrderMapper iOrderMapper) {
        this.iOrderMapper = iOrderMapper;
    }

    @Override
    public PageInfo<Order> getOrderPageByParam(RequestDTO requestDTO) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        Order order = objectMapper.convertValue(requestDTO.getObject(), Order.class);
        queryWrapper.eq(DELETE_COLUMN, NOT_DELETED);
        if (order != null && StringUtils.isNotEmpty(order.getOrderNumber())) {
            queryWrapper.like(ORDER_NUMBER_COLUMN, order.getOrderNumber());
        }
        if (order != null && StringUtils.isNotEmpty(order.getRechargeAccount())) {
            queryWrapper.like(RECHARGE_ACCOUNT_COLUMN, order.getRechargeAccount());
        }
        if (StringUtils.isNotEmpty(requestDTO.getBeginTime()) && StringUtils.isNotEmpty(requestDTO.getEndTime())) {
            queryWrapper.between(CREATE_TIME_COLUMN, requestDTO.getBeginTime(), requestDTO.getEndTime());
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

    private Page<OrderInfo> getAppOrderInfo(RequestDTO requestDTO, OrderInfo orderInfo) {
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
            try {
                Distributionrelation distributionrelation = distributionrelationMapper.selectByPrimaryKey(userId);
                User u = userMapper.selectByPrimaryKey(distributionrelation.getSuperId());
                info.setSuperUser(u.getNickName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (info.getSuperUser() == null) {
                    info.setSuperUser("无");
                }
            }
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
            Example example2 = new Example(SmsTemplate.class);
            example.createCriteria().andEqualTo("orderNumber", order.getOrderNumber());
            List<SmsTemplate> smsTemplates = smsTemplateMapper.selectByExample(example2);
            for (SmsTemplate smsTemplate : smsTemplates) {
                info.setItemDefects(smsTemplate.getOther());
            }
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
            /*2020-05-18 00:00:00
            2020-05-19 23:59:59*/
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
        } /*else {
                return new Page<OrderInfo>(list, list.size());
            }*/
    }
}

