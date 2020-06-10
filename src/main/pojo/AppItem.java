package pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class AppItem extends Base{
    private static final long serialVersionUID = 7305778520915848839L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appItemId;

    @TableField(exist = false)
    private Integer itemId;
    private Integer itemCategoryId;
    @Transient
    @TableField(exist = false)
    private String itemCategoryName;
    private String itemName ;
    private String itemUnit ;
    private BigDecimal price;
    private String commodityExplain ;
    private String duration ;
    private String picture ;
    private Integer isShow;
    private BigDecimal promotionOriginalCost;
    private LocalDateTime promotionBeginDate;
    private LocalDateTime promotionEndDate;
    private Integer active;
    @Transient
    @TableField(exist = false)
    private Integer quantity;
    private String bargainType;
    public enum BargainType{
        TIME_LIMIT_SALE,
        NORMAL
    }
    public static final String ID_COLUMN =  "app_item_id";
    public static final String SHOW_COLUMN = "is_show";
    public static final String BARGAIN_TYPE_COLUMN = "bargain_type";

}
