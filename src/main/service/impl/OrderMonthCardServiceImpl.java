package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.Constant;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.OrderMonthCardMapper;
import org.springframework.stereotype.Service;
import pojo.UserCoupon;
import pojo.activity.Activity;
import pojo.order.OrderMonthCard;
import pojo.order.OrderMonthCardVo;
import service.IOrderMonthCardService;

import java.time.LocalDateTime;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.order.Order.CREATE_TIME_COLUMN;
import static pojo.order.Order.ORDER_NUMBER_COLUMN;
import static pojo.order.OrderMonthCard.USER_ID_COLUMN;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/14 0014下午 3:37
 */
@Service
public class OrderMonthCardServiceImpl implements IOrderMonthCardService {
    private OrderMonthCardMapper orderMonthCardMapper;
    private ObjectMapper objectMapper;

    public OrderMonthCardServiceImpl(OrderMonthCardMapper orderMonthCardMapper, ObjectMapper objectMapper) {
        this.orderMonthCardMapper = orderMonthCardMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ReturnData<PageInfo<OrderMonthCardVo>> getOrderPageByParam(RequestDTO requestDTO) {
        String orderNumber = null;
        String userPhone = null;
        LocalDateTime start = null;
        LocalDateTime end = null;
        QueryWrapper<OrderMonthCardVo> queryWrapper = new QueryWrapper();
        queryWrapper.eq(OrderMonthCard.DELETED_COLUMN, Constant.NOT_DELETED);
        try {
            OrderMonthCardVo orderMonthCardVo = objectMapper.convertValue(requestDTO.getData(), OrderMonthCardVo.class);
            if (orderMonthCardVo !=null ) {
                if (StringUtils.isNotEmpty(orderMonthCardVo.getOrderNumber())) {
                    orderNumber = orderMonthCardVo.getOrderNumber();
                }
                if (StringUtils.isNotEmpty(orderMonthCardVo.getUserPhone())){
                    userPhone = orderMonthCardVo.getUserPhone();
                }
            }

            if (requestDTO.getStartLocalDateTime() !=null  && requestDTO.getEndLocalDateTime() !=null ) {
                start = requestDTO.getStartLocalDateTime();
                end = requestDTO.getEndLocalDateTime();
            }
            PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
            List<OrderMonthCardVo> list = orderMonthCardMapper.getOrderPageByParam(orderNumber,userPhone,start,end);
            return new ReturnData<>(SUCCESS_CODE,"操作成功",new PageInfo<OrderMonthCardVo>(list));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ReturnData<>(Fail_CODE,"操作失败",null);
        }
    }


    @Override
    public List<OrderMonthCard> getOrderListByParam(RequestDTO requestDTO) {
        QueryWrapper<OrderMonthCard> queryWrapper = new QueryWrapper();
        queryWrapper.eq(OrderMonthCard.DELETED_COLUMN, Constant.NOT_DELETED);
        OrderMonthCard orderMonthCard = null;
        try {
            orderMonthCard = objectMapper.convertValue(requestDTO.getData(), OrderMonthCard.class);
            if (orderMonthCard != null && StringUtils.isNotEmpty(orderMonthCard.getOrderNumber())) {
                queryWrapper.like(ORDER_NUMBER_COLUMN,orderMonthCard.getOrderNumber());
            }

            if (StringUtils.isNotEmpty(requestDTO.getBeginTime()) && StringUtils.isNotEmpty(requestDTO.getEndTime()) ) {
                queryWrapper.between(CREATE_TIME_COLUMN,requestDTO.getBeginTime(),requestDTO.getEndTime());
            }

            if (orderMonthCard != null && StringUtils.isNotEmpty(orderMonthCard.getActive().toString())) {
                queryWrapper.like(OrderMonthCard.ACTIVE,orderMonthCard.getActive());
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        List<OrderMonthCard> list = orderMonthCardMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public ReturnData<Boolean> update(OrderMonthCard orderMonthCard) {
        UpdateWrapper<OrderMonthCard> updateWrapper = new UpdateWrapper<OrderMonthCard>();
        updateWrapper.eq(UserCoupon.ID,orderMonthCard.getId());
        try {
            if (orderMonthCardMapper.update(orderMonthCard, updateWrapper) > 0) {
                return new ReturnData(SUCCESS_CODE, "操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE, "操作失败", false);
    }
}
