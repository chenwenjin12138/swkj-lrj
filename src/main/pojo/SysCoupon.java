package pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author fl
 * @descrip: 红包类型
 * @date 2020/5/13 0013下午 5:14
 */
@Data
public class SysCoupon {
    private int sysCouponId;
    /**
     * 红包类型（商品id集合）
     */
    private String type;

    /**
     * 红包时效（天）
     */
    private Integer aging;

    /**
     * 红包面额
     */
    private BigDecimal denomination;

    /**
     * 使用说明
     */
    private String instructions;

    /**
     * 1表示删除
     */
    private Integer active;

    public static final String ID_COLUMN = "sys_coupon_id";
}
