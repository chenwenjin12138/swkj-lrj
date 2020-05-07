package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pojo.user.AppStaff;
import pojo.user.AppUser;
import service.IAppStaffService;
import service.IAppUserService;

/**
 * @author : fl
 * @describe : app用户管理控制类
 * @date : 2020-4-27
 */
@RestController("/appStaff")
@AllArgsConstructor
public class AppStaffController {
    private IAppStaffService iAppStaffService;

    /**
     * 分页查询所有app员工
     * @return
     */
    @PostMapping("/appStaffPageByParam")
    public PageInfo<AppStaff> getAppUser(@RequestBody RequestDTO requestDTO){
       return iAppStaffService.getAppUserPageByParam(requestDTO);
    }

    /**
     * 修改员工
     * @return
     */
    @PostMapping("/updateAppStaff")
    public ReturnData<Boolean> updateAppStaff(@RequestBody AppStaff appStaff){
        return iAppStaffService.updateAppStaff(appStaff);
    }

    /**
     * 新增员工
     * @return
     */
    @PostMapping("/addAppStaff")
    public ReturnData<Boolean> addAppUser(@RequestBody AppStaff appStaff){
        return iAppStaffService.addAppStaff(appStaff);
    }


}
