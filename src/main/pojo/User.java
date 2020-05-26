package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/15 10:46
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class User extends Base{
    private static final long serialVersionUID = 4614457906889718376L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appUserId;
    private String userPhone;
    private String userPassword;
    private String nickName;
    private String headPhoto;
    private String deviceToken;
    private Byte active;
    private Byte isCheck;
    private Byte isYearsService;
    private Integer yearsServiceType;
    private Integer residueCount;
    private String yearsServiceStartTime;
    private String yearsServiceEndTime;
    @Transient
    private BigDecimal backMoney;
    /**返利来源*/
    @Transient
    private String source;
    /**下级用户*/
    @Transient
    private String lowName;
    /**1.普通用户 2.商家*/
    private Byte type;

    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CREATE_TIME = "createTime";
    public static final Integer COLUMN_TYPE_2 = 2;
    public static final String COLUMN_NICK_NAME = "nickName";
    public static final String COLUMN_USER_PHONE = "userPhone";
    public static final String COLUMN_ACTIVE = "active";
}
