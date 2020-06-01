package pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fl
 * @descrip: 用户红包
 * @date 2020/5/14 0014上午 9:25
 */
@Data
public class UserCoupon {
    private Integer id; // 自增主键
    private Integer userId;// 用户ID
    private Integer sysCouponId;// 属于系统哪种红包
    private LocalDateTime createTime; //创建时间
    private LocalDateTime limitTime;// 过期时间
    private Integer active; //是否可用  0：不可用   1：可用

    /**
     * 红包获得方式：1.后台发放红包，2.订单分享赠送，3.唤醒红包
     */
    private Integer source;
    /**
     * 红包类型
     */
    private Integer couponType;
    private BigDecimal denomination;

    public static final String USER_ID_COLUMN = "user_id";

}
