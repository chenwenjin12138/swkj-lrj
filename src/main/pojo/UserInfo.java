package pojo;

/**
 * @author : cwj
 * @describe : 用户实体类
 * @date : 2020-3-17
 */
public class UserInfo {

    private Integer appUserId; // 用户Id
    private String userPhone = ""; // 用户电话
    private String userPassword = ""; // 用户密码
    private String nickName = ""; // 用户昵称
    private String headPhoto = ""; // 头像
    private String deviceToken = ""; //手机唯一识别码
    private Integer active; //
    private String createTime = ""; // 创建时间
    private Integer ischeck;
    private String checkTime = "";
    private Integer isYearsService;

    public UserInfo(){

    }

    public UserInfo(Integer appUserId, String userPhone, String userPassword, String nickname, String headPhoto, String deviceToken, Integer active, String createTime, Integer ischeck, String checkTime, Integer isYearsService) {
        this.appUserId = appUserId;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.nickName = nickname;
        this.headPhoto = headPhoto;
        this.deviceToken = deviceToken;
        this.active = active;
        this.createTime = createTime;
        this.ischeck = ischeck;
        this.checkTime = checkTime;
        this.isYearsService = isYearsService;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIscheck() {
        return ischeck;
    }

    public void setIscheck(Integer ischeck) {
        this.ischeck = ischeck;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getIsYearsService() {
        return isYearsService;
    }

    public void setIsYearsService(Integer isYearsService) {
        this.isYearsService = isYearsService;
    }
}
