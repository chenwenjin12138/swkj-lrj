package pojo.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @descrip: 活动使用详情
 * @date 2020/5/25 0025下午 2:59
 */
@Data
@ApiModel
public class ActivityTime {
    private int id;
    private Integer activityId;
    /**
     * 1 日 2 周  3月
     */
    @ApiModelProperty(value = "活动周期 1每日 2每周 3每月")
    private int period;
    @ApiModelProperty(value = "具体时间")
    private int day;
    @ApiModelProperty("活动开始时段")
    private String beginTime;
    @ApiModelProperty("活动结束时段")
    private String endTime;

    public static final String ID_COLUMN = "id";

}
