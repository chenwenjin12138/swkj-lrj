package service;

import org.springframework.stereotype.Service;
import pojo.SysAuthority;
import pojo.UserInfo;

import java.util.List;

/**
 * @author : cwj
 * @describe : 用户服务层
 * @date : 2020-3-18
 */
@Service("userInfoService")
public interface IUserInfoService {
    /**
     * 根据手机号查询用户信息
     * @param userPhone
     * @return
     */
    UserInfo getUserInfoByLoginInfo(String userPhone);

    /**
     * 根据用户Id查询用户权限
     */
    List<SysAuthority> getAdminPermissionList(Long adminId);

}
