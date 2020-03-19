package pojo;

/**
 * @author : cwj
 * @describe : 系统权限
 * @date : 2020-3-19
 */
public class SysAuthority {
    private Integer sysAuthorityId; // 权限Id
    private String authorityName = ""; // 权限名称
    private Integer authorityPid; //
    private String authorityDescride = ""; // 权限简介
    private String authorityUrl = ""; // 权限路径
    private Integer active; // 状态 1:可用，启用 0：不可用，禁用


    public Integer getSysAuthorityId() {
        return sysAuthorityId;
    }

    public void setSysAuthorityId(Integer sysAuthorityId) {
        this.sysAuthorityId = sysAuthorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Integer getAuthorityPid() {
        return authorityPid;
    }

    public void setAuthorityPid(Integer authorityPid) {
        this.authorityPid = authorityPid;
    }

    public String getAuthorityDescride() {
        return authorityDescride;
    }

    public void setAuthorityDescride(String authorityDescride) {
        this.authorityDescride = authorityDescride;
    }

    public String getAuthorityUrl() {
        return authorityUrl;
    }

    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
