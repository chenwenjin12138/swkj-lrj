package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import pojo.order.OrderCustomHouse;

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
    PageInfo<OrderCustomHouse> getPageByParam(RequestDTO requestDTO);


}
