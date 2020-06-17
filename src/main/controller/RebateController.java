package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pojo.Balance;
import pojo.Rebate;
import service.IBalanceService;
import service.RebateService;

import java.math.BigDecimal;

/**
 * @author : fl
 * @describe : app用户管理控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/rebate")
@AllArgsConstructor
@Api(tags = "返利管理")
public class RebateController {
    private RebateService rebateService;

    /**
     * 查询用户返利
     * @param requestDTO
     * @return
     */
    @PostMapping("/rebate")
    @ApiOperation(value = "查询返利信息,查询条件：用户Id,返利时间")
    public PageInfo<Rebate> getPageByParam(@RequestBody RequestDTO requestDTO) {
        return rebateService.getPageByParam(requestDTO);
    }

    @PostMapping("/getTotalWithdraw")
    @ApiOperation("返利总结查询")
    public BigDecimal update(String userId){
        return rebateService.getTotalRebate(Integer.parseInt(userId));
    }

}
