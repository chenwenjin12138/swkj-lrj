package pojo.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fl
 * @descrip: 订单
 * @date 2020/5/11 0011下午 4:39
 */
@Data
@TableName("`order`")
@Table(name = "`order`")
public class Order implements Serializable {
    private static final long serialVersionUID = -8137629380534512272L;
    @Id
    private Integer id;//主键
    private String orderNumber; //  订单号
    private Integer userId; // 用户Id
    private BigDecimal totalPrice; // 总价
    private Integer status; // 订单状态
    private Integer payStatus; //  支付状态
    private String createTime; // 生成时间
    private Integer userCouponId = 0; //红包Id
    private Integer orderType;       //订单类型  1.洗衣订单 2.洗衣月卡订单  3.单项家政服务  4.定制家政服务 5 充值订单
    @Transient
    private Integer deleted = 0;  //1删除
    @Transient
    private String createUser; //创建人
    @Transient
    private String rechargeAccount; //充值手机账户
    private Byte isShare;//是否分享
    //TODO LXH 添加字段注释，如果是数据库字段在数据库里添加
    @TableField(exist = false)
    private String traceStatus;
    @Transient
    private String ew;

    public static final String ORDER_NUMBER_COLUMN = "order_number";
    public static final String RECHARGE_ACCOUNT_COLUMN = "recharge_account";
    public static final String DELETE_COLUMN = "deleted";
    public static final String CREATE_TIME_COLUMN = "create_time";
    public static final String ID_COLUMN = "id";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String ORDER_TYPE_COLUMN = "order_type";
}
