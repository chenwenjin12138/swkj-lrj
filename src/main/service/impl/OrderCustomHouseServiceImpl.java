package service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import mapper.OrderCustomHouseMapper;
import org.springframework.stereotype.Service;
import pojo.order.OrderCustomHouseVo;
import service.IOrderCustomHouseService;

import java.time.LocalDateTime;
import java.util.List;

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
}
