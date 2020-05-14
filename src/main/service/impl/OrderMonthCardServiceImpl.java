package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import mapper.OrderMonthCardMapper;
import org.springframework.stereotype.Service;
import pojo.order.Order;
import pojo.order.OrderMonthCard;
import service.IOrderMonthCardService;

import java.util.List;

import static common.Constant.NOT_DELETED;
import static pojo.order.Order.*;
import static pojo.order.Order.CREATE_TIME_COLUMN;
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
    public PageInfo<OrderMonthCard> getOrderPageByParam(RequestDTO requestDTO) {
        QueryWrapper<OrderMonthCard> queryWrapper = new QueryWrapper();
        OrderMonthCard orderMonthCard = objectMapper.convertValue(requestDTO.getObject(),OrderMonthCard.class);
        if (orderMonthCard != null && StringUtils.isNotEmpty(orderMonthCard.getOrderNumber())) {
            queryWrapper.like(ORDER_NUMBER_COLUMN,orderMonthCard.getOrderNumber());
        }

        if (StringUtils.isNotEmpty(requestDTO.getBeginTime()) && StringUtils.isNotEmpty(requestDTO.getEndTime()) ) {
            queryWrapper.between(CREATE_TIME_COLUMN,requestDTO.getBeginTime(),requestDTO.getEndTime());
        }

        if (orderMonthCard != null && StringUtils.isNotEmpty(orderMonthCard.getActive().toString())) {
            queryWrapper.like(USER_ID_COLUMN,orderMonthCard.getUserId());
        }

        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<OrderMonthCard> list = orderMonthCardMapper.selectList(queryWrapper);
        return new PageInfo<OrderMonthCard>(list);
    }
}
