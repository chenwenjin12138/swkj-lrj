package controller;

import dto.RequestDTO;
import dto.ReturnData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.SysCoupon;
import pojo.UserCoupon;
import service.ISysCouponService;
import service.IUserCouponService;

import java.util.List;

/**
 * @author : fl
 * @describe : 用户红包管理控制类
 * @date : 2020-5-13
 */
@RestController
@RequestMapping("/UserCoupon")
@AllArgsConstructor
public class UserCouponController {
    private IUserCouponService userCouponService;

    /**
     * 查找用户红包
     * @param requestDTO 查询条件
     * @return
     */
    @GetMapping("/getListByParam")
   public List<UserCoupon> getListByParam(RequestDTO requestDTO){
        return userCouponService.getListByParam(requestDTO);
    }

}
