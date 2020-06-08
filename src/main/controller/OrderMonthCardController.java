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
import pojo.order.OrderMonthCardVo;
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
    public PageInfo<OrderMonthCardVo> getOrderPageByParam(@RequestBody RequestDTO requestDTO){
      return monthCardService.getOrderPageByParam(requestDTO);
    }


}
