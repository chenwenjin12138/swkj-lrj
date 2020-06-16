package pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("app_rebate")
@ApiModel("用户返利")
public class Rebate extends Base {
    private static final long serialVersionUID = -576832470510366714L;
    @Id
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer userId;
    /**分享的用户id*/
    @ApiModelProperty("分享的用户id")
    private Integer lowId;
    @ApiModelProperty("返利金额")
    private BigDecimal backMoney;
    /**1.普通用户 2.商家*/
    @ApiModelProperty("用户类型 1.普通用户 2.商家*/")
    private Byte type;
    /**资金来源*/
    private String source = "";
    @ApiModelProperty("订单号")
    private String orderNumber;


    /**
     * 数据库字段名
     */
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_USER_ID1 = "user_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CREATE_TIME = "createTime";
    public static final String COLUMN_CREATE_TIME1 = "create_time";

}
