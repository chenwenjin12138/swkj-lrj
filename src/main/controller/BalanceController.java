package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
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
@RestController("/balance")
@AllArgsConstructor
public class BalanceController {
    private IBalanceService iBalanceService;

    /**
     * 查询用户余额
     * @param userId
     * @return
     */
    @GetMapping("/balance")
    public Balance findByUserId(@RequestParam String userId) {
        return iBalanceService.findByUserId(userId);
    }


}
