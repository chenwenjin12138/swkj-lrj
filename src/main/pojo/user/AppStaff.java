package pojo.user;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppStaff {
    private Integer appStaffId;
    private String staffUser = "";
    private String staffPassword = "";
    private String realName = "";
    private String telephone = "";
    private String headPhoto = "";
    private String registerTime = "";
    /**
     * 0 禁用 1 启用
     */
    private Integer active;
    /**
     *1 懒人家员工 2 商家员工  3分拣人员
     */
    private Integer type;

    /**
     * 员工抢单范围
     */
    private Double removing = new Double(0);

    /**
     *经度
     */
    private Double lng = new Double(0);

    /**
     * 纬度
     */
    private Double lat = new Double(0);

    /**
     * 商家地址
     */
    private String address = "";

    /**
     * 删除状态 1已删除 0 未删除
     */
    private int isDeleted = 0;


    /**
     * 数据库字段名
     */
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_REAL_NAME = "real_name";
    public static final String COLUMN_STAFF_USER = "staff_user";
    public static final String COLUMN_APP_STAFF_ID = "app_staff_id";
    public static final String COLUMN_IS_DELETED = "is_deleted";

}
