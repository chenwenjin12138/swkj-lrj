package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import pojo.order.Order;
import service.IOrderService;
import vo.OrderInfo;
import vo.Page;

import javax.annotation.Resource;

/**
 * @Description: 后台管理系统订单信息管理控制层
 * @Author Lxh
 * @Date 2020/5/15 21:23
 */
@RestController
@RequestMapping("order")
@AllArgsConstructor
@Api(tags = "订单信息管理")
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * @Description: Order信息分页查询
     * @Author: LxH
     * @Date: 2020/5/15 21:31
     */
    @ApiOperation(value = "Order信息分页查询")
    @RequestMapping("getAppOrderInfoPageByParam")
    public Page<OrderInfo> getAppOrderInfoPageByParam(OrderInfo orderInfo, RequestDTO requestDTO){
        return orderService.getAppOrderInfoPageByParam(orderInfo, requestDTO);
    }

    /**
     * @Description: 短信群发功能
     * @Author: LxH
     * @Date: 2020/5/20 17:08
     */
    @ApiOperation(value = "短信群发功能")
    @RequestMapping("sendMessages")
    public ReturnData sendMessages(@RequestParam("orderNumber[]") String [] orderNumbers,
                                   @RequestParam("phoneNumber[]") String [] phoneNumbers){
        return orderService.sendMessages(orderNumbers,phoneNumbers);
    }

    /**
     * @Description: 获取订单商品信息
     * @Author: LxH
     * @Date: 2020/5/29 11:21
     */
    @ApiOperation(value = "获取订单商品信息")
    @RequestMapping("findOrderItem")
    public ReturnData findOrderItem(OrderInfo orderInfo){
        return orderService.findOrderItem(orderInfo);
    }

    @ApiOperation(value = "充值订单查询",notes = "orderType字段传5")
    @PostMapping("/getOrderPageByParam")
    public PageInfo<Order> getOrderPageByParam(@RequestBody RequestDTO requestDTO){
        return orderService.getOrderPageByParam(requestDTO);
    }


}


