package controller;

import com.baomidou.mybatisplus.extension.api.R;

import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Rebate;
import pojo.User;
import service.MerchantManagementService;
import vo.Page;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/25 10:57
 */
@RestController
@AllArgsConstructor
@Api(tags = "后台商家管理模块")
@RequestMapping("merchant")
public class MerchantManagementController {

    @Resource
    private MerchantManagementService merchantManagementService;

    /**
     * @Description: 分页查询
     * @Author: LxH
     * @Date: 2020/5/25 14:07
     */
    @ApiOperation(value = "分页查询")
    @RequestMapping("getMerchantManagementPageByParam")
    public Page<User> getMerchantManagementPageByParam(RequestDTO<User> requestDTO,User user){
        return merchantManagementService.getMerchantManagementPageByParam(requestDTO,user);
    }

    /**
     * @Description: 添加商户
     * @Author: LxH
     * @Date: 2020/5/26 15:26
     */
    @ApiOperation(value = "添加商户")
    @RequestMapping("addMerchant")
    public ReturnData addMerchant(User user){
        return merchantManagementService.addMerchant(user);
    }

    /**
     * @Description: 修改商家信息
     * @Author: LxH
     * @Date: 2020/5/26 15:48
     */
    @ApiOperation(value = "修改商家信息")
    @RequestMapping("updateMerchant")
    public ReturnData updateMerchant(User user){
        return merchantManagementService.updateMerchant(user);
    }

    /**
     * @Description: 删除商户
     * @Author: LxH
     * @Date: 2020/5/26 15:58
     */
    @ApiOperation(value = "删除商户")
    @RequestMapping("deleteMerchantById")
    public ReturnData deleteMerchantById(@RequestParam("id[]")Integer[] userIds){
        return merchantManagementService.deleteMerchantById(userIds);
    }
}
