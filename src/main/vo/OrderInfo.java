package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Description: 后台管理订单
 * @Author Lxh
 * @Date 2020/5/16 12:22
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    /**判断是否是查询  是1 不是仅展示分页结果填null*/
    private Integer inquire;
    /**查询时间*/
    private String inquireTime;
    private String superUser;
    private String orderNumber;
    private Integer userId;
    private String userPhone;
    private String nickname;
    private BigDecimal totalPrice;
    /**下单时间*/
    private String underOrderTime;
    /**支付状态*/
    private Integer payStatus;
    /**分享状态*/
    private Byte isShare;
    /******************* 员工信息 ********************/
    private Integer appStaffId;
    private String staffUser;
    private String staffPassword;
    private String realName;
    private String telephone;
    private String registerTime;
    /**抢单时间*/
    private String takeOrderTime;
    /******************* 优惠信息 ********************/
    private BigDecimal actualPayPrice;
    private String remark;
    private Integer useCouponId;
    private Integer denomination;
    /**余额*/
    private BigDecimal useBalance;
    private BigDecimal useGivenBalance;
    private String useGivenBalanceInfo;
    private Integer isUrgent;
    private BigDecimal urgentFee;
    private Integer isServiceCharge;
    private BigDecimal serviceCharge;
    /******************* 退款信息 ********************/
    private Integer refund;
    /******************* 追中状态信息 ******************/
    private Integer appTraceStatusId;
    private String traceStatusName;
    private Integer type;

}
