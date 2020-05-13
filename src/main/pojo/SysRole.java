package pojo;

import lombok.ToString;

/**
 * @author : cwj
 * @describe : 管理后台角色
 * @date : 2020-3-18
 */
@ToString
public class SysRole {
    private Integer sysRoleId; // 角色ID
    private String roleName = ""; // 角色名称
    private String roleDescride = ""; // 角色简介
    private String sysRoleAuthoritys = ""; //角色具有的权限串
    private Integer active; // 状态 1:可用，启用 0：不可用，禁用

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescride() {
        return roleDescride;
    }

    public void setRoleDescride(String roleDescride) {
        this.roleDescride = roleDescride;
    }

    public String getSysRoleAuthoritys() {
        return sysRoleAuthoritys;
    }

    public void setSysRoleAuthoritys(String sysRoleAuthoritys) {
        this.sysRoleAuthoritys = sysRoleAuthoritys;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public static final String SYS_ROLE_ID_COLUMN = "sys_role_id";
    public static final String SYS_AUTHORITY_ID_COLUMN = "sys_authority_id";
}
