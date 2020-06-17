package pojo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fl
 * @Description: 商家用户管理
 * @date 2020/5/6 0006上午 10:57
 */
@Data
@ApiModel("商家用户信息")
public class SysAdmin {
    private Integer sysAdminId;
    /**
     * 商家账号
     */
    @ApiModelProperty("商家账号")
    private String adminName;
    private String adminPassword;
    private Integer active;
    @ApiModelProperty("用户角色 1管理员 2后台客服 3财务核对 4商家用户 5推广账号")
    private Integer sysAdminRoleType;
    private String createTime;
    @ApiModelProperty("商家邀请码")
    private String invitationCode; /* '商家邀请码 三期', */
    @ApiModelProperty("商家用户 1 商家用户 0 其他用户")
    private Integer sysType;/* '1.商家用户 null或其他为系统后台用户 三期', */
    @ApiModelProperty("商家名称")
    private String businessName;/* '商家名称 三期', */
    @ApiModelProperty("商家地址")
    private String businessAddress;/* '商家地址 三期', */
    @ApiModelProperty("商家联系电话")
    private String businessPhone; /* '商家联系电话 三期', */
    @ApiModelProperty("商铺联系人")
    private String businessContactPerson;/* '商铺联系人', */
    @ApiModelProperty("商铺信息")
    private String businessInfo;/* '商铺信息', */
    @ApiModelProperty("分销比例")
    private BigDecimal businessDistributionRatio;/* 分销比例*/

    /**
     * 数据库字段名
     */
    public static final String COLUMN_ID = "sys_admin_id";
    public static final String COLUMN_BUSINESS_PHONE = "business_phone";
    public static final String COLUMN_BUSINESS_NAME = "admin_name";
    public static final String COLUMN_BUSINESS_CONTACT_PERSON = "business_distribution_ratio";
    public static final String COLUMN_CREATE_TIME = "create_time";

}
