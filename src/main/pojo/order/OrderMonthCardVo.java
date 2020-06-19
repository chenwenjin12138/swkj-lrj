package pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fl
 * @descrip:
 * @date 2020/6/8 0008上午 10:01
 */
@Data
@ApiModel("洗衣月卡订单")
public class OrderMonthCardVo {

    @ApiModelProperty("订单id")
    private Integer id; //  订单号

    @ApiModelProperty("订单号")
    private String orderNumber; //  订单号

    @ApiModelProperty("用户Id")
    private Integer userId; // 用户Id

    @ApiModelProperty("总价")
    private BigDecimal totalPrice; // 总价

    @ApiModelProperty("订单状态 0未完成 1已完成 ")
    private Integer status; // 订单状态

    @ApiModelProperty("支付状态 0 ：未支付   1：支付")
    private Integer payStatus; //  支付状态

    @ApiModelProperty("红包Id")
    private Integer userCouponId = 0; //红包Id

    @ApiModelProperty("订单类型  1.洗衣订单 2.洗衣月卡订单  3.单项家政服务  4.定制家政服务 5 充值订单")
    private Integer orderType;       //订单类型  1.洗衣订单 2.洗衣月卡订单  3.单项家政服务  4.定制家政服务 5 充值订单

    @ApiModelProperty("是否分享")
    private Byte isShare;//是否分享

    @ApiModelProperty("电话号码")
    private String userPhone;

    @ApiModelProperty("月卡id")
    private String monthCardId;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty("用户月卡剩余使用次数")
    private Integer userMonthCardCount;

    @ApiModelProperty("月卡可用次数")
    private Integer count;

    @ApiModelProperty("月卡名称")
    private String name;

    @ApiModelProperty("月卡价格")
    private BigDecimal price;

    @ApiModelProperty("月卡状态 0 不能用 1 能用")
    private Integer active;

}
