package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.OrderComment;

/**
 * @author : fl
 * @describe : 用户评论管理
 * @date : 2020-5-12
 */
public interface IOrderCommentService {

    /**
     * 根据条件查询评论信息
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<OrderComment> getOrderCommentPageByParam(RequestDTO requestDTO);

    /**
     * 修改订单信息（删除,通过）
     * @param order
     * @return
     */
    ReturnData<Boolean> updateOrder(OrderComment order);


}
