package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/25 11:11
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_rebate")
public class Rebate extends Base {
    private static final long serialVersionUID = -576832470510366714L;
    @Id
    private Integer appFeedBackId;

    private Integer userId;
    /**分享的用户id*/
    private Integer lowId;

    private BigDecimal backMoney;
    /**1.普通用户 2.商家*/
    private Byte type;
    /**资金来源*/
    private String source = "";

    /**
     * 数据库字段名
     */
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CREATE_TIME = "createTime";

}
