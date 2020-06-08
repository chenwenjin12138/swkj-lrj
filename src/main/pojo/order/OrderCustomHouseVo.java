package pojo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fl
 * @descrip:
 * @date 2020/6/8 0008上午 10:01
 */
@Data
@ApiModel("定制家政月卡订单")
public class OrderCustomHouseVo {
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

    @ApiModelProperty("创建时间")
    private LocalDateTime openTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("基础服务次数")
    private Integer baseServiceCount;

    @ApiModelProperty("基础服务价格")
    private BigDecimal baseServicePrice;

    @ApiModelProperty("服务周期 1-12 单位是月")
    private String serviceCycle;

    @ApiModelProperty("服务时间 0周末 1工作日 2不限")
    private String workTime;

    @ApiModelProperty("房租面积")
    private String houseArea;

    @ApiModelProperty("服务项")
    private String individualServiceJson;


}
