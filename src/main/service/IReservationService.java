package service;


import dto.RequestDTO;
import dto.ReturnData;
import pojo.Reservation;

import java.util.List;

/**
 * @author : fl
 * @describe : 订单管理
 * @date : 2020-5-11
 */
public interface IReservationService {

    /**
     * 根据条件预约信息
     * @param requestDTO 查询条件
     * @return
     */
    List<Reservation> getReservationPageByParam(RequestDTO requestDTO);

    /**
     * 修改订单信息（删除）
     * @param Reservation
     * @return
     */
    ReturnData<Boolean> updateReservation(Reservation Reservation);

    /**
     * 添加订单信息
     * @param Reservation
     * @return
     */
    ReturnData<Boolean> addReservation(Reservation Reservation);



}
