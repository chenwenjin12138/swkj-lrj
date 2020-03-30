package service;

import org.springframework.stereotype.Service;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import pojo.UserInfo;

import java.util.List;

/**
 * @author : cwj
 * @describe : 用户服务层
 * @date : 2020-3-18
 */
public interface ISysUserInfoService {
    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SysUser getUserInfoByLoginInfo(String userName);

    /**
     * 根据用户ID查询用户具有权限
     */
    List<SysAuthority> getSysAuthoritysByAdmin(SysUser sysUser);


    /**
     * 根据用户ID 查询用户角色
     */
    SysRole getSysRoleByAdminId(Integer adminId);

    /**
     * 根据角色ID 查询角色权限
     */
    SysAuthority getAuthorityByRoleId(Integer roleId);

}
