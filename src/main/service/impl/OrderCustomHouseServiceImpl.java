package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import mapper.OrderCustomHouseMapper;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Service;
import pojo.order.OrderCustomHouse;
import pojo.order.OrderMonthCard;
import service.IOrderCustomHouseService;

import java.util.List;

import static pojo.order.Order.CREATE_TIME_COLUMN;
import static pojo.order.Order.ORDER_NUMBER_COLUMN;
import static pojo.order.OrderMonthCard.USER_ID_COLUMN;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/14 0014下午 5:02
 */
@Service
public class OrderCustomHouseServiceImpl implements IOrderCustomHouseService {
    private ObjectMapper objectMapper;
    private OrderCustomHouseMapper orderCustomHouseMapper;

    public OrderCustomHouseServiceImpl(ObjectMapper objectMapper, OrderCustomHouseMapper orderCustomHouseMapper) {
        this.objectMapper = objectMapper;
        this.orderCustomHouseMapper = orderCustomHouseMapper;
    }

    @Override
    public PageInfo<OrderCustomHouse> getPageByParam(RequestDTO requestDTO) {
        QueryWrapper<OrderCustomHouse> queryWrapper = new QueryWrapper();
        OrderCustomHouse orderMonthCard = objectMapper.convertValue(requestDTO.getObject(),OrderCustomHouse.class);
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
        List<OrderCustomHouse> list = orderCustomHouseMapper.selectList(queryWrapper);
        return new PageInfo<OrderCustomHouse>(list);
    }
}
