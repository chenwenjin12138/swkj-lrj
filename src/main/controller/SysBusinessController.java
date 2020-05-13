package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
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
public class SysBusinessController {
    private IBusinessAdminService iBusinessAdminService;

    /**
     * 分页查询所有app员工
     * @return
     */
    @PostMapping("/businessPageByParam")
    public PageInfo<SysAdmin> getAppUser(@RequestBody RequestDTO requestDTO){
       return iBusinessAdminService.getBusinessAdminPageByParam(requestDTO);
    }

    /**
     * 修改员工
     * @return
     */
    @PostMapping("/updateBusiness")
    public ReturnData<Boolean> updateBusiness(@RequestBody SysAdmin sysAdmin){
        return iBusinessAdminService.updateBusinessAdmin(sysAdmin);
    }

    /**
     * 新增员工
     * @return
     */
    @PostMapping("/addBusiness")
    public ReturnData<Boolean> addBusiness(@RequestBody SysAdmin sysAdmin){
        return iBusinessAdminService.addBusinessAdmin(sysAdmin);
    }

}
