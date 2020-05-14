package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.order.Order;
import pojo.order.OrderMonthCard;
import service.IOrderMonthCardService;
import service.IOrderService;

/**
 * @author : fl
 * @describe : 月卡管理控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private IOrderService iOrderService;

    /**
     * 分页查询所有月卡订单
     * @return
     */
    @PostMapping("/getOrderPageByParam")
    public PageInfo<Order> getOrderPageByParam(@RequestBody RequestDTO requestDTO){
       return iOrderService.getOrderPageByParam(requestDTO);
    }


}
