package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pojo.AppItem;
import pojo.MonthCard;
import pojo.order.CustomHouseService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 后台管理订单
 * @Author Lxh
 * @Date 2020/5/16 12:22
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = -6632503311815645708L;
    /**判断分页查询订单类型  1.单项洗衣家政  2.月卡定制家政*/
    private Integer findType;
    /**判断是否是查询  是1 不是仅展示分页结果填null*/
    private Integer inquire;
    /**查询时间*/
    private String inquireTime;
    private String orderNumber;
    private Integer userId;
    private String userPhone;
    private String nickname;
    private BigDecimal totalPrice;
    /**下单时间*/
    private String underOrderTime;
    /**支付状态*/
    private String payStatusName;
    private Byte payStatus;
    /**分享状态*/
    private String shareName;
    private Byte isShare;
    /**抢单状态*/
    private String statusName;
    private Byte status;
    /**订单状态*/
    private String isEndName;
    private Byte isEnd;
    /******************* 员工信息 ********************/
    private Integer appStaffId;
    private String staffUser;
    private String realName;
    private String telephone;
    private String registerTime;
    /**抢单时间*/
    private String takeOrderTime;
    /**取衣时间*/
    private String getClothesTime;
    /**实际取衣时间*/
    private String reGetClothesTime;
    /**送回时间*/
    private String sendBackTime;
    /******************* 优惠信息 ********************/
    private BigDecimal actualPayPrice;
    private String remark;
    private Integer useCouponId;
    private Integer denomination;
    /**余额*/
    private BigDecimal useBalance;
    private BigDecimal useGivenBalance;
    private String useGivenBalanceInfo;
    private BigDecimal urgentFee;
    private BigDecimal serviceCharge;
    /******************* 退款信息 ********************/
    private Integer refund;
    /******************* 追中状态信息 ******************/
    private String traceStatusName;
    private Integer type;
    /**商品瑕疵*/
    private String itemDefects;
    /**洗衣订单详情*/
    private String takeConsignee;
    private String takeUserName;
    private String takePhone;
    private String sendConsignee;
    private String sendUserName;
    private String sendPhone;
    private List<ItemJson> listItems;

    /**单项家政商品信息*/
    private AppItem houseItem;

    /**月卡 定制家政 开始结束时间*/
    private String startTime;
    private String endTime;
    /**月卡*/
    private MonthCard monthCard;
    private Integer userMonthCardCount;
    /**定制家政*/
    private CustomHouseService customHouseService;

}
