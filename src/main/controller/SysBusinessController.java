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
import pojo.user.AppStaff;
import pojo.user.SysAdmin;
import service.IAppStaffService;
import service.IBusinessAdminService;

/**
 * @author : fl
 * @describe : app商家管理理控制类
 * @date : 2020-5-7
 */
@RestController
@RequestMapping("/sysBusiness")
@AllArgsConstructor
@Api(tags = "商家信息管理")
public class SysBusinessController {
    private IBusinessAdminService iBusinessAdminService;

    /**
     * 分页查询所有商家信息
     * @return
     */
    @PostMapping("/businessPageByParam")
    @ApiOperation(value = "分页查询所有商家",notes = "查询条件：商家名称、商家联系电话")
    public PageInfo<SysAdmin> getAppUser(@RequestBody RequestDTO requestDTO){
       return iBusinessAdminService.getBusinessAdminPageByParam(requestDTO);
    }

    /**
     * 修改商家信息
     * @return
     */
    @PostMapping("/updateBusiness")
    @ApiOperation("修改商家信息")
    public ReturnData<Boolean> updateBusiness(@RequestBody SysAdmin sysAdmin){
        return iBusinessAdminService.updateBusinessAdmin(sysAdmin);
    }

    /**
     * 新增商家
     * @return
     */
    @PostMapping("/addBusiness")
    @ApiOperation("新增商家")
    public ReturnData<Boolean> addBusiness(@RequestBody SysAdmin sysAdmin){
        return iBusinessAdminService.addBusinessAdmin(sysAdmin);
    }

}
