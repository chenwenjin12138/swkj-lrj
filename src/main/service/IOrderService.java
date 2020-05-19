package service;


import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;

import pojo.order.Order;
import vo.OrderInfo;
import vo.Page;

/**
 * @author : fl
 * @describe : 订单管理
 * @date : 2020-5-11
 */
public interface IOrderService {

    /**
     * 根据条件订单信息
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<Order> getOrderPageByParam(RequestDTO requestDTO);

    /**
     * 修改订单信息（删除）
     * @param order
     * @return
     */
    ReturnData<Boolean> updateOrder(Order order);

    /**
     * 添加订单信息
     * @param order
     * @return
     */
    ReturnData<Boolean> addOrder(Order order);


    /**
     * 充值订单退款
     * @param order
     * @return
     */
    ReturnData<Boolean> refund(Order order);

    /**
     * @Description: Order信息分页查询
     * @Author: LxH
     * @Date: 2020/5/15 21:37
     */
    Page<OrderInfo> getAppOrderInfoPageByParam(OrderInfo orderInfo, RequestDTO requestDTO);
}
