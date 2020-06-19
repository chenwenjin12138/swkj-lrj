package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.user.AppStaff;
import service.IAppStaffService;


/**
 * @author : fl
 * @describe : app用户管理控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/appStaff")
@AllArgsConstructor
@Api(tags = "APP员工管理")
public class AppStaffController {

    private IAppStaffService iAppStaffService;

    /**
     * 分页查询所有app员工
     * @return
     */
    @ApiOperation(value = "分页查询app员工")
    @PostMapping("/appStaffPageByParam")
    public ReturnData<PageInfo<AppStaff>> getAppUser(@RequestBody RequestDTO requestDTO){
        return iAppStaffService.getAppUserPageByParam(requestDTO);
    }

    /**
     * 修改员工
     * @return
     */
    @ApiOperation(value = "修改员工信息",notes = "要求传入整个员工对象,不仅是修改过的值")
    @PostMapping("/updateAppStaff")
    public ReturnData<Boolean> updateAppStaff(@RequestBody AppStaff appStaff){
        return iAppStaffService.updateAppStaff(appStaff);
    }

    /**
     * 新增员工
     * @return
     */
    @ApiOperation(value = "添加员工信息",notes = "要求传入整个员工对象")
    @PostMapping("/addAppStaff")
    public ReturnData<Boolean> addAppUser(@RequestBody AppStaff appStaff){
        return iAppStaffService.addAppStaff(appStaff);
    }


}
