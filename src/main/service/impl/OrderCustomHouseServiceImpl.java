package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.OrderCustomHouseMapper;
import org.springframework.stereotype.Service;
import pojo.UserCoupon;
import pojo.order.OrderCustomHouse;
import pojo.order.OrderCustomHouseVo;
import pojo.order.OrderMonthCard;
import service.IOrderCustomHouseService;

import java.time.LocalDateTime;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.order.Order.CREATE_TIME_COLUMN;
import static pojo.order.Order.ORDER_NUMBER_COLUMN;

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
    public PageInfo<OrderCustomHouseVo> getPageByParam(RequestDTO requestDTO) {
        String orderNumber = null;
        String userPhone = null;
        LocalDateTime start = null;
        LocalDateTime end = null;
        OrderCustomHouseVo orderCustomHouseVo = objectMapper.convertValue(requestDTO.getObject(),OrderCustomHouseVo.class);
        if (orderCustomHouseVo !=null ) {
            if (StringUtils.isNotEmpty(orderCustomHouseVo.getOrderNumber())) {
                orderNumber = orderCustomHouseVo.getOrderNumber();
            }
            if (StringUtils.isNotEmpty(orderCustomHouseVo.getUserPhone())){
                userPhone = orderCustomHouseVo.getUserPhone();
            }
        }

        if (requestDTO.getStartLocalDateTime() !=null  && requestDTO.getEndLocalDateTime() !=null ) {
            start = requestDTO.getStartLocalDateTime();
            end = requestDTO.getEndLocalDateTime();
        }
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<OrderCustomHouseVo> list = orderCustomHouseMapper.getOrderPageByParam(orderNumber,userPhone,start,end);
        return new PageInfo<OrderCustomHouseVo>(list);
    }

    @Override
    public List<OrderCustomHouse> getCustomHouseListByParam(RequestDTO requestDTO) {
        QueryWrapper<OrderCustomHouse> queryWrapper = new QueryWrapper();
        OrderCustomHouse orderCustomHouse = objectMapper.convertValue(requestDTO.getObject(),OrderCustomHouse.class);

        if (orderCustomHouse != null && StringUtils.isNotEmpty(orderCustomHouse.getActive().toString())) {
            queryWrapper.like(OrderMonthCard.ACTIVE,orderCustomHouse.getActive());
        }
       return orderCustomHouseMapper.selectList(queryWrapper);
    }

    @Override
    public ReturnData<Boolean> update(OrderCustomHouse orderCustomHouse) {
        UpdateWrapper<OrderCustomHouse> updateWrapper = new UpdateWrapper<OrderCustomHouse>();
        updateWrapper.eq(UserCoupon.ID,orderCustomHouse.getId());
        try {
            if (orderCustomHouseMapper.update(orderCustomHouse, updateWrapper) > 0) {
                return new ReturnData(SUCCESS_CODE, "操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE, "操作失败", false);
    }
}
