package controller;

import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "红包管理")
public class SysCouponController {
    private ISysCouponService sysCouponService;

    /**
     * 查找红包类型
     * @param requestDTO 查询条件
     * @return
     */
    @GetMapping("/getListByParam")
    @ApiOperation(value = "查询红包类型",notes = "无参数")
    public List<SysCoupon> getListByParam(RequestDTO requestDTO){
        return sysCouponService.getListByParam(requestDTO);
    }

    /**
     * 添加
     * @param sysCoupon
     * @return
     */

    @PostMapping("/add")
    @ApiOperation("新增红包类型")
    public ReturnData<Boolean> add(SysCoupon sysCoupon){
        return  sysCouponService.add(sysCoupon);
    }


    /**
     * 修改
     * @param sysCoupon
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改红包类型",notes = "active传1时表示删除")
    public ReturnData<Boolean> update(SysCoupon sysCoupon){
        return sysCouponService.update(sysCoupon);
    }





}
