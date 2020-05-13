package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
    private Integer itemCategoryId;
    @Transient
    private String itemCategoryName;
    private String itemName ;
    private String itemUnit ;
    private BigDecimal price;
    private String commodityExplain ;
    private String duration ;
    private String picture ;
    private Integer isShow;
    private BigDecimal promotionOriginalCost;
    private String promotionBeginDate;
    private String promotionEndDate;

}
