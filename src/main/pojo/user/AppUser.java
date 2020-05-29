package pojo.user;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AppUser {
    private Integer appUserId;
    private String userPhone = "";
    private String userPassword = "";
    private String nickName = "";
    private String headPhoto = "";
    /**
     * 手机唯一识别码
     */
    private String deviceToken = "";
    /**
     * 状态 1:可用，启用 0：不可用，禁用
     */
    private Integer active;
    private String createTime;
    private Integer isYearsService;

    /**
     * 是否会员
     */
    @TableField(exist = false)
    private Integer isMember;

    /**
     * 数据库字段名
     */
    public static final String COLUMN_APP_USER_ID = "app_user_id";
    public static final String COLUMN_USER_PHONE = "user_phone";
    public static final String COLUMN_CREATE_TIME = "create_time";
    public static final String Column_ACTIVE ="active";
}
