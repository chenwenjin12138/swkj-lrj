package pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Lxh
 * @date 2020/3/20 15:01
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_item")
@ApiModel("商品信息")
public class AppItem extends Base{
    private static final long serialVersionUID = 7305778520915848839L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appItemId;

    @TableField(exist = false)
    private Integer itemId;
    @ApiModelProperty("种类Id")
    private Integer itemCategoryId;
    @Transient
    @TableField(exist = false)
    private String itemCategoryName;
    @ApiModelProperty("商品名称")
    private String itemName ;

    @ApiModelProperty("商品单位")
    private String itemUnit ;

    @ApiModelProperty("商品现价")
    private BigDecimal price;

    @ApiModelProperty("商品洗护内容")
    private String commodityExplain ;

    @ApiModelProperty("洗衣时间")
    private String duration ;

    @ApiModelProperty("图片 1显示 0 不显示")
    private String picture ;

    @ApiModelProperty("是否显示 1显示 0 不显示")
    private Integer isShow;
    @ApiModelProperty("商品原价")
    private BigDecimal promotionOriginalCost;

    @ApiModelProperty("商品特价开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime promotionBeginDate;

    @ApiModelProperty("商品特价结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime promotionEndDate;

    @ApiModelProperty("0 禁用 1启用")
    private Integer active;

    @Transient
    @TableField(exist = false)
    private Integer quantity;

    @ApiModelProperty("商品特价类型 TIME_LIMIT_SALE 显示特价 NORMAL普通")
    @Transient
    private String bargainType;

    public enum BargainType{
        TIME_LIMIT_SALE,
        NORMAL
    }
    public static final String ID_COLUMN =  "app_item_id";
    public static final String SHOW_COLUMN = "is_show";
    public static final String BARGAIN_TYPE_COLUMN = "bargain_type";

}
