package mapper;

import org.springframework.stereotype.Repository;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;

import java.util.List;

/**
 * @author : cwj
 * @describe : ISysManagementMapper 的映射接口
 * @date :  2020-3-26
 */
@Repository
public interface ISysManagementMapper {

    /**
     * 查询所有账号
     */
    List<SysUser> getAccountList();

    /**
     * 查询所有角色
     */
    List<SysRole> getRoleList();
    /**
     * 查询所有权限
     */
    List<SysAuthority> getAuthorityList();

    /**
     * 添加系统 用户
     */
    Integer insertSysUser(SysUser sysUser);

    /**
     * 通过Id查询系统 用户
     * @param sysAdminId
     * @return
     */
    SysUser getSysUserById(String sysAdminId);

    /**
     * 更新系统用户信息
     * @param sysUser
     * @return
     */
    Integer updateSysUser(SysUser sysUser);
}
