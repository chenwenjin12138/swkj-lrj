package service;

import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;

import java.util.List;

/**
 * @author : cwj
 * @describe : 系统：后台管理
 * @date : 2020-3-26
 */
public interface ISysManagementService {
    /**
     * 查询所有账号
     */
    List<SysUser> findAccountList();

    /**
     * 查询所有角色
     */
    List<SysRole> findRoleList();
    /**
     * 查询所有权限
     */
    List<SysAuthority> findAuthorityList();
    /**
     * 根据用户名查询用户信息
     */
    SysUser findAccountByAdminName(String adminName);


    /**
     * 后台 添加系统用户
     */
    Integer addSysUser(SysUser sysUser);

}
