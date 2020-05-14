package controller;

import dto.RequestDTO;
import dto.ReturnData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.SysCoupon;
import service.ISysCouponService;

import java.util.List;

/**
 * @author : fl
 * @describe : 红包类型管理控制类
 * @date : 2020-5-13
 */
@RestController
@RequestMapping("/SysCoupon")
@AllArgsConstructor
public class SysCouponController {
    private ISysCouponService sysCouponService;

    /**
     * 查找红包类型
     * @param requestDTO 查询条件
     * @return
     */
    @GetMapping("/getListByParam")
   public List<SysCoupon> getListByParam(RequestDTO requestDTO){
        return sysCouponService.getListByParam(requestDTO);
    }

    /**
     * 添加
     * @param sysCoupon
     * @return
     */

    @PostMapping("/add")
    public ReturnData<Boolean> add(SysCoupon sysCoupon){
        return  sysCouponService.add(sysCoupon);
    }


    /**
     * 修改
     * @param sysCoupon
     * @return
     */
    @PostMapping("/update")
    public ReturnData<Boolean> update(SysCoupon sysCoupon){
        return sysCouponService.update(sysCoupon);
    }





}
