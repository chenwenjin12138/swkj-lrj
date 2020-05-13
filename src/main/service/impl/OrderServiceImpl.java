package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.IOrderMapper;
import org.springframework.stereotype.Service;
import pojo.Order;
import service.IOrderService;

import java.util.List;

import static common.Constant.NOT_DELETED;
import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.Order.*;
import static pojo.user.AppUser.COLUMN_APP_USER_ID;

/**
 * @author fl
 * @descrip: 订单管理实现类
 * @date 2020/5/11 0011下午 4:42
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private IOrderMapper iOrderMapper;
    private ObjectMapper objectMapper = new ObjectMapper();

    public OrderServiceImpl(IOrderMapper iOrderMapper) {
        this.iOrderMapper = iOrderMapper;
    }
    @Override
    public PageInfo<Order> getOrderPageByParam(RequestDTO requestDTO) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        Order order = objectMapper.convertValue(requestDTO.getObject(),Order.class);
        queryWrapper.eq(DELETE_COLUMN,NOT_DELETED);
        if (order != null && StringUtils.isNotEmpty(order.getOrderNumber())) {
            queryWrapper.like(ORDER_NUMBER_COLUMN,order.getOrderNumber());
        }
        if (order != null && StringUtils.isNotEmpty(order.getRechargeAccount())) {
            queryWrapper.like(RECHARGE_ACCOUNT_COLUMN, order.getRechargeAccount());
        }
        if (StringUtils.isNotEmpty(requestDTO.getBeginTime()) && StringUtils.isNotEmpty(requestDTO.getEndTime()) ) {
            queryWrapper.between(CREATE_TIME_COLUMN,requestDTO.getBeginTime(),requestDTO.getEndTime());
        }
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<Order> list = iOrderMapper.selectList(queryWrapper);
        return new PageInfo<Order>(list);
    }


    @Override
    public ReturnData<Boolean> updateOrder(Order order) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<Order>();
        updateWrapper.eq(COLUMN_APP_USER_ID, order.getId());
        if (iOrderMapper.update(order, updateWrapper) > 0 ) {
            return new ReturnData(SUCCESS_CODE,"操作成功", true);
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
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
}
