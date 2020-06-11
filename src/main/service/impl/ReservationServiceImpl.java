package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.ReservationMapper;
import org.springframework.stereotype.Service;
import pojo.Reservation;
import pojo.order.Order;
import service.IReservationService;

import java.util.List;

import static common.Constant.NOT_DELETED;
import static pojo.order.Order.DELETE_COLUMN;
import static pojo.order.Order.ORDER_NUMBER_COLUMN;

/**
 * @author fl
 * @descrip:预约订单类
 * @date 2020/6/10 0010上午 9:47
 */
@Service
public class ReservationServiceImpl implements IReservationService {
    private ObjectMapper objectMapper;
    private ReservationMapper reservationMapper;

    public ReservationServiceImpl(ObjectMapper objectMapper, ReservationMapper reservationMapper) {
        this.objectMapper = objectMapper;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<Reservation> getReservationPageByParam(RequestDTO requestDTO) {
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper();
        if (requestDTO.getObject() !=null ) {
            Reservation reservation = objectMapper.convertValue(requestDTO.getObject(), Reservation.class);
        }
        if (requestDTO.getStartLocalDateTime() !=null && requestDTO.getEndLocalDateTime() !=null) {
            queryWrapper.between(Reservation.COLUMN_CREATE_TIME1,requestDTO.getStartLocalDateTime(),requestDTO.getEndLocalDateTime());
        }
        return reservationMapper.selectList(queryWrapper);
    }

    @Override
    public ReturnData<Boolean> updateReservation(Reservation Reservation) {
        return null;
    }

    @Override
    public ReturnData<Boolean> addReservation(Reservation Reservation) {
        return null;
    }
}
