package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pojo.Balance;
import pojo.user.AppUser;
import service.IAppUserService;
import service.IBalanceService;

/**
 * @author : fl
 * @describe : app用户管理控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/balance")
@AllArgsConstructor
@Api(tags = "余额管理")
public class BalanceController {
    private IBalanceService iBalanceService;

    /**
     * 查询用户余额
     * @param userId
     * @return
     */
    @GetMapping("/balance")
    @ApiOperation(value = "查询用户余额")
    public Balance findByUserId(@RequestParam String userId) {
        return iBalanceService.findByUserId(userId);
    }


}
