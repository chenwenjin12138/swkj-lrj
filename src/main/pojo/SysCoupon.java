package pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fl
 * @descrip: 红包类型
 * @date 2020/5/13 0013下午 5:14
 */
@Data
@ApiModel("红包类型")
public class SysCoupon {
    private int sysCouponId;
    /**
     * 红包类型
     */
    @ApiModelProperty(value = "红包类型 ：2衣物，3袋洗，4鞋子，5家政，1通用, 6月卡 ")
    private int type;

    /**
     * 红包时效（天）
     */
    @ApiModelProperty(value = "红包时效（天） ")
    private Integer aging;

    /**
     * 红包面额
     */
    @ApiModelProperty(value = "红包面额")
    private BigDecimal denomination;

    /**
     * 使用说明
     */
    @ApiModelProperty(value = "使用说明")
    private String instructions;

    /**
     * 1表示删除
     */
    @ApiModelProperty(value = "1表示删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    public static final String ID_COLUMN = "sys_coupon_id";
    public static final String IS_DELETED_COLUMN = "is_deleted";
    public static final String TYPE_COLUMN = "type";
}
