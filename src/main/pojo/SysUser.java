package pojo;


/**
 * @author : cwj
 * @describe :管理后台员工表
 * @String :  2020-3-18
 */
public class SysUser {
    private Integer sysAdminId; // id
    private String adminName = ""; // 名字
    private String adminPassword = ""; // 密码
    private Integer active; //  状态：启动，禁用
    private Integer sysAdminRoleType; // 角色类别
    private String sysAdminRoles; // 用户具有的角色串
    private String createTime;    //创建时间
    private Integer sysType; //
    private String invitationCode ="";//商家邀请码
    private String businessName = "";//商家名称
    private String businessAddress = ""; //商家地址
    private String businessPhone = ""; //商家联系电话
    private String businessContactPerson = ""; //商铺联系人
    private String businessInfo = ""; //商铺信息
    private Double businessDistributionRatio = 0.00; //分销比例

    public SysUser(){

    }

    public SysUser(Integer sysAdminId, String adminName, String adminPassword, Integer active, Integer sysAdminRoleType, String sysAdminRoles, String createTime, Integer sysType, String invitationCode, String businessName, String businessAddress, String businessPhone, String businessContactPerson, String businessInfo, Double businessDistributionRatio) {
        this.sysAdminId = sysAdminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.active = active;
        this.sysAdminRoleType = sysAdminRoleType;
        this.sysAdminRoles = sysAdminRoles;
        this.createTime = createTime;
        this.sysType = sysType;
        this.invitationCode = invitationCode;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessPhone = businessPhone;
        this.businessContactPerson = businessContactPerson;
        this.businessInfo = businessInfo;
        this.businessDistributionRatio = businessDistributionRatio;
    }

    public Integer getSysAdminId() {
        return sysAdminId;
    }

    public void setSysAdminId(Integer sysAdminId) {
        this.sysAdminId = sysAdminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getSysAdminRoleType() {
        return sysAdminRoleType;
    }

    public void setSysAdminRoleType(Integer sysAdminRoleType) {
        this.sysAdminRoleType = sysAdminRoleType;
    }

    public String getSysAdminRoles() {
        return sysAdminRoles;
    }

    public void setSysAdminRoles(String sysAdminRoles) {
        this.sysAdminRoles = sysAdminRoles;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessContactPerson() {
        return businessContactPerson;
    }

    public void setBusinessContactPerson(String businessContactPerson) {
        this.businessContactPerson = businessContactPerson;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

    public Double getBusinessDistributionRatio() {
        return businessDistributionRatio;
    }

    public void setBusinessDistributionRatio(Double businessDistributionRatio) {
        this.businessDistributionRatio = businessDistributionRatio;
    }
}
