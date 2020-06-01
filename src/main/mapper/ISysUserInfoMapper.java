package mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import pojo.UserInfo;

import java.util.List;

/**
 * @author : cwj
 * @describe : ISysUserInfoMapper的映射接口
 * @date : 2020-3-18
 */
@Repository
public interface ISysUserInfoMapper {
    @Select("SELECT * FROM sys_admin WHERE admin_name=#{userName}")
    SysUser getUserInfoByLoginInfo(String userName);

    /**
     *通过Id 查询角色
     * @param roleId
     * @return
     */
    SysRole getRoleById(Integer roleId);

    /**
     * 通过Id 查询权限
     */
    SysAuthority getAuthoritById(Integer sysAuthorityId);

}
