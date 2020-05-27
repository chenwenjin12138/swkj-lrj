package pojo.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @descrip: 活动
 * @date 2020/5/25 0025下午 2:59
 */
@Data
@ApiModel
public class Activity {
    @TableId(type = IdType.AUTO)
    private int id;
    @ApiModelProperty(value = "活动名称")
    private String name;
    @ApiModelProperty(value = "活动内容")
    private String content;
    /**
     * 活动展示方式 1.弹框 2 链接
     */
    @ApiModelProperty(value = "活动展示方式 1.弹框 2.链接")
    private int showType;
    @ApiModelProperty(value = "活动开始日期",example = "2020-05-05")
    private String beginDate;
    @ApiModelProperty(value = "活动结束日期",example="2020-05-10")
    private String endDate;

    @ApiModelProperty(value = "活动图片")
    private String photo;

    /**
     * 活动状态 0 停用 1 启用
     */
    @ApiModelProperty(value = " 活动状态 0 停用 1 启用")
    private String active;
    /**
     * 活动链接地址
     */
    @ApiModelProperty(value = "活动链接地址")
    private String link;

    @ApiModelProperty(value = "删除标识 0未删除 1 已删除")
    private int deleted;

    private String createUser;
    private String createTime;

    public static final String NAME_COLUMN = "name";
    public static final String DELETED_COLUMN ="deleted";
}
