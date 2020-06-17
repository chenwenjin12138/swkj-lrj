package pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fl
 * @Description: 订单流水
 * @date 2020/5/7 0007下午 3:17
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "pay_operation")
@ApiModel("流水记录")
public class PayOperation {
    @Id
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 支付渠道 1.余额2.微信3.支付宝
     */
    @ApiModelProperty("支付渠道 1.余额2.微信3.支付宝")
    private Integer tradeSource;
    /**
     * 1 支付  -1 退款  2 提现
     */
    @ApiModelProperty("流水类型： 1 支付  -1 退款  2 提现")
    private Integer tradeType;
    /**
     *付款银行
     */
    private String bankType;
    /**
     * 金额
     */
    @ApiModelProperty("金额")
    private BigDecimal totalFee;
    /**
     *第三方支付订单号
     */
    @ApiModelProperty("金额")
    private String transactionId;
    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private String outTradeNo;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("用户id")
    private Integer userId;

    /**
     * 审核状态 0 待审核 1审核通过 -1 不通过
     */
    @ApiModelProperty("审核状态 0 待审核 1审核通过 -1 不通过")
    private Integer checkStatus = 0 ;

    @ApiModelProperty("审核原因")
    private String reason;

    @ApiModelProperty("用户电话号码")
    private String userPhone;

    public static final String TRADE_SOURCE_COLUMN = "trade_source";
    public static final String TRADE_TYPE_COLUMN = "trade_type";
    public static final String CHECK_STATUS_COLUMN = "check_status";
    public static final String USER_PHONE_COLUMN = "user_phone";
}
