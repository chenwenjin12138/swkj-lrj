package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.order.Order;
import pojo.order.OrderMonthCard;
import pojo.order.OrderMonthCardVo;

import java.util.List;

/**
 * @author : fl
 * @describe : 月卡订单管理
 * @date : 2020-5-11
 */
public interface IOrderMonthCardService {

    /**
     * 根据条件月卡订单信息
     * @param requestDTO 查询条件
     * @return
     */
    ReturnData<PageInfo<OrderMonthCardVo>> getOrderPageByParam(RequestDTO requestDTO);

    List<OrderMonthCard> getOrderListByParam(RequestDTO requestDTO);


    ReturnData<Boolean> update(OrderMonthCard orderMonthCard);
}
