package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.user.AppUser;

import java.util.List;

/**
 * @author : fl
 * @describe : APP用户管理
 * @date : 2020-4-28
 */
public interface IAppUserService {

    /**
     * 根据条件查询用户信息
     * @param requestDTO 查询条件
     * @return
     */
    ReturnData<PageInfo<AppUser>> getAppUserPageByParam(RequestDTO requestDTO);

    List<AppUser> getAppUserListByParam(RequestDTO requestDTO);

    /**
     * 修改App用户对象
     * @param appUser
     * @return
     */
    ReturnData<Boolean> updateAppUser(AppUser appUser);
}
