package pojo;

import lombok.Data;

/**
 * @author fl
 * @descrip: 用户红包
 * @date 2020/5/14 0014上午 9:25
 */
@Data
public class UserCoupon {
    private Integer userId;// 用户ID
    private Integer couponId; // 自增主键
    private Integer sysCouponId;// 属于系统哪种红包
    private String createTime; //创建时间
    private String limitTime;// 过期时间
    private Integer active; //是否可用  0：不可用   1：可用

    public static final String USER_ID_COLUMN = "user_id";
}
