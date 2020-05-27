package pojo.activity;

import com.sun.xml.internal.bind.v2.model.core.ID;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @descrip: 活动详情
 * @date 2020/5/25 0025下午 2:59
 */
@Data
@ApiModel
public class ActivityItem {
    private int id;
    private int activityId;
    /**
     * 减免类型 1折扣 2具体金额
     */
    @ApiModelProperty(value = "减免类型 1折扣 2具体金额")
    private Integer reduceType;
    /**
     * 减免额度 -1表示额度不固定
     */
    @ApiModelProperty(value = "减免金额,不固定时传-1")
    private int reduceQuota;
    /**
     * 活动用户 1新用户 2老用户 3所有用户
     */
    @ApiModelProperty(value = "活动用户 1新用户 2老用户 3所有用户")
    private Integer userType;
    /**
     * 活动数量
     */
    @ApiModelProperty(value = "活动数量 不限量时传-1")
    private Integer activityNumber;
    /**
     * 商品种类Id
     */
    @ApiModelProperty(value = "商品种类Id")
    private String appItemCategoryId;
    /**
     * 商品Id
     */
    @ApiModelProperty(value = "商品Id")
    private String appItemId;

    public static final String ACTIVITY_ID_COLUMN = "activity_id";
}
