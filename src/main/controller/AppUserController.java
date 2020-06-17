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
import pojo.user.AppUser;
import service.IAppUserService;

/**
 * @author : fl
 * @describe : app用户管理控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/appUser")
@AllArgsConstructor
@Api(tags = "APP用户管理")
public class AppUserController {
    private IAppUserService iAppUserService;
    /**
     * 分页查询所有app用户
     * @return
     */
    @PostMapping("/appUserPageByParam")
    @ApiOperation(value = "分页查询app用户")
    public ReturnData<PageInfo<AppUser>> getAppUser(@RequestBody RequestDTO requestDTO){
       return iAppUserService.getAppUserPageByParam(requestDTO);
    }

    /**
     * 修改app用户
     * @return
     */
    @PostMapping("/updateAppUser")
    @ApiOperation(value = "修改app用户")
    public ReturnData<Boolean> updateAppUser(@RequestBody AppUser appUser){
        return iAppUserService.updateAppUser(appUser);
    }


}
