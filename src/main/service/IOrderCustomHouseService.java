package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.order.OrderCustomHouse;
import pojo.order.OrderCustomHouseVo;
import pojo.order.OrderMonthCard;

import java.util.List;

/**
 * @author : fl
 * @describe : 定制加政月卡订单管理
 * @date : 2020-5-11
 */
public interface IOrderCustomHouseService {

    /**
     * 根据条件月卡订单信息
     * @param requestDTO 查询条件
     * @return
     */
    ReturnData<PageInfo<OrderCustomHouseVo>> getPageByParam(RequestDTO requestDTO);


    List<OrderCustomHouse> getCustomHouseListByParam(RequestDTO requestDTO);

    ReturnData<Boolean> update(OrderCustomHouse orderCustomHouse);
}
