package pojo;

/**
 * @author : cwj
 * @describe : 管理后台角色
 * @date : 2020-3-18
 */
public class SysRole {
    private Integer sysRoleId; // 角色ID
    private String roleName = ""; // 角色名称
    private String roleDescride = ""; // 角色简介
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
