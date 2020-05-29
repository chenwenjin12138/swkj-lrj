package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.order.OrderCustomHouse;
import pojo.order.OrderMonthCard;
import service.IOrderCustomHouseService;
import service.IOrderMonthCardService;

/**
 * @author : fl
 * @describe : 家政月卡管理控制类
 * @date : 2020-5-14
 */
@RestController
@RequestMapping("/orderCustomHouse")
@AllArgsConstructor
@Api(tags = "家政月卡管理")
public class OrderCustomHouseController {
    private IOrderCustomHouseService orderCustomHouseService;

    /**
     * 分页查询所有家政月卡订单
     * @return
     */
    @PostMapping("/orderCustomHouse")
    @ApiOperation(value = "分页查询所有家政月卡订单",notes = "查询条件：订单编号，订单创建时间")
    public PageInfo<OrderCustomHouse> getPageByParam(@RequestBody RequestDTO requestDTO){
       return orderCustomHouseService.getPageByParam(requestDTO);
    }



}
