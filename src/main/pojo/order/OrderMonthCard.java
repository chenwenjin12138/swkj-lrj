package pojo.order;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fl
 * @descrip: 月卡订单
 * @date 2020/5/14 0014下午 3:30
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_month_card")
@ApiModel("洗衣月卡信息")
public class OrderMonthCard {
    @TableId
    @javax.persistence.Id
    @Column(name = "id")
    private Integer Id;
    /**
     * 订单号
     */
    @ApiModelProperty("洗衣月卡信息")
    private String orderNumber;

    @ApiModelProperty("用户Id")
    private String userId;

    /**
     * 0 不能用 1 能用
     */
    @ApiModelProperty("月卡状态 0 不能用 1 能用")
    private Integer active;
    /**
     * 月卡类型id
     */
    @ApiModelProperty("月卡类型id")
    private String monthCardId;

    /**
     *月卡使用次数
     */
    @ApiModelProperty("月卡使用次数")
    private Integer userMonthCardCount;

    /**
     * 使用详情
     */
    @ApiModelProperty("月卡使用详情")
    private String userMonthCardItemJson;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("失效失败")
    private String endTime;

    @ApiModelProperty("删除标识 0 未删除 1 已删除")
    private Integer deleted;

    public static final String USER_ID_COLUMN = "user_id";
    public static final String COLUMN_ORDER_NUMBER = "orderNumber";
    public static final String COLUMN_MONTH_CARD_ID= "monthCardId";
    public static final String ACTIVE = "active";
    public static final String DELETED_COLUMN ="deleted";

}
