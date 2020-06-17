package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Rebate;
import pojo.order.OrderMonthCard;
import pojo.order.OrderMonthCardVo;
import pojo.user.AppStaff;
import service.IOrderMonthCardService;

/**
 * @author : fl
 * @describe : 月卡订单管理控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/orderMonthCard")
@AllArgsConstructor
@Api(tags = "洗衣月卡订单")
public class OrderMonthCardController {
    private IOrderMonthCardService monthCardService;

    /**
     * 分页查询所有月卡订单
     * @return
     */
    @PostMapping("/orderMonthCard")
    @ApiOperation("分页查询所有月卡订单")
    public ReturnData<PageInfo<OrderMonthCardVo>> getOrderPageByParam(@RequestBody RequestDTO requestDTO){
      return monthCardService.getOrderPageByParam(requestDTO);
    }

    @ApiOperation(value = "修改月卡")
    @PostMapping("/updateOrderMonthCard")
    public ReturnData<Boolean> updateOrderMonthCard(@RequestBody OrderMonthCard orderMonthCard){
        return monthCardService.update(orderMonthCard);
    }
}
