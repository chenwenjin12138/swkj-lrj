package service.impl;

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
import pojo.AppStaffOrder;
import pojo.Distributionrelation;
import pojo.User;
import pojo.order.Order;
import pojo.user.AppStaff;
import service.IOrderService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;
import vo.OrderInfo;
import vo.Page;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
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
        if (orderInfo.getInquire()==null) {
            startTime = DateUtils.getStartTime();
            endTime = DateUtils.getEndTime();
            requestDTO.setBeginTime(startTime).setEndTime(endTime);
            return getAppOrderInfo(requestDTO, orderInfo);
        }
        requestDTO.setBeginTime(startTime).setEndTime(endTime);
        return getAppOrderInfo(requestDTO, orderInfo);
    }

    private Page<OrderInfo> getAppOrderInfo(RequestDTO requestDTO, OrderInfo orderInfo) {
        ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0).andBetween("createTime", requestDTO.getBeginTime(),requestDTO.getEndTime());
        int start = requestDTO.getPage() * requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start, requestDTO.getSize());
        List<Order> orders = iOrderMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (Order order : orders) {
            OrderInfo info = new OrderInfo();
            info.setIsShare(order.getIsShare()).setOrderNumber(order.getOrderNumber()).setPayStatus(order.getPayStatus()).setUnderOrderTime(order.getCreateTime()).setType(order.getOrderType()).setTotalPrice(order.getTotalPrice());
            String orderNumber = order.getOrderNumber();
            BigInteger orderId = new BigInteger(orderNumber);
            Integer userId = order.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            Distributionrelation distributionrelation = distributionrelationMapper.selectByPrimaryKey(userId);
            User u = userMapper.selectByPrimaryKey(distributionrelation.getSuperId());
            info.setUserId(userId).setNickname(user.getNickName()).setUserPhone(user.getUserPhone()).setSuperUser(u.getNickName());
            Example e = new Example(AppStaffOrder.class);
            e.createCriteria().andEqualTo("orderId", orderId);
            List<AppStaffOrder> appStaffOrders = appStaffOrderMapper.selectByExample(e);
            for (AppStaffOrder appStaffOrder : appStaffOrders) {
                info.setTakeOrderTime(appStaffOrder.getCreateTime()).setAppStaffId(appStaffOrder.getStaffId());
                AppStaff appStaff = staffMapper.selectByPrimaryKey(appStaffOrder.getStaffId());
                info.setTelephone(appStaff.getTelephone()).setRealName(appStaff.getRealName()).setRegisterTime(appStaff.getRegisterTime()).setStaffUser(appStaff.getStaffUser()).setStaffPassword(appStaff.getStaffPassword());
            }
            list.add(info);
        }
        if (orderInfo.getInquire()==1) {
            String number = orderInfo.getOrderNumber();
            String userPhone = orderInfo.getUserPhone();
            Integer type = orderInfo.getType();
            String inquireTime = orderInfo.getInquireTime();
            /*2020-05-18 00:00:00
            2020-05-19 23:59:59*/
            ArrayList<OrderInfo> infos = new ArrayList<OrderInfo>();

            for (OrderInfo reOrderInfo : list) {
                if (number!=null&&userPhone!=null) {
                    if (number.equals(reOrderInfo.getOrderNumber())&&userPhone.equals(reOrderInfo.getUserPhone())) {
                        infos.add(reOrderInfo);
                    }
                }
                if (type!=null) {
                    if (type.equals(reOrderInfo.getType())) {
                        infos.add(reOrderInfo);
                    }
                }
                if (inquireTime!=null) {
                    if (inquireTime.equals(reOrderInfo.getUnderOrderTime().substring(0,10))) {
                        infos.add(reOrderInfo);
                    }
                }
            }
            return new Page<OrderInfo>(infos, infos.size());
        }
        return new Page<OrderInfo>(list, list.size());
    }
}
