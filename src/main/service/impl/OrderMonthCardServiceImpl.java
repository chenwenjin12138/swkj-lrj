package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import mapper.OrderMonthCardMapper;
import org.springframework.stereotype.Service;
import pojo.order.OrderMonthCardVo;
import service.IOrderMonthCardService;

import java.time.LocalDateTime;
import java.util.List;

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
    public PageInfo<OrderMonthCardVo> getOrderPageByParam(RequestDTO requestDTO) {
        String orderNumber = null;
        String userPhone = null;
        LocalDateTime start = null;
        LocalDateTime end = null;
        QueryWrapper<OrderMonthCardVo> queryWrapper = new QueryWrapper();
        OrderMonthCardVo orderMonthCardVo = objectMapper.convertValue(requestDTO.getObject(),OrderMonthCardVo.class);
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
        return new PageInfo<OrderMonthCardVo>(list);
    }
}
