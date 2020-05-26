package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/8 19:36
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wx_month_card")
public class MonthCard extends Base {

    private static final long serialVersionUID = -2451899579844246553L;

    /**就是id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    /**下面这些认不得*/
    private Integer count;

    private String name;
    private BigDecimal price;
    private Byte status;
    private Float shareDicount;
    private String notice;
    @Transient
    private List<String> appItemCategoryName;
    @Transient
    private List<Integer> categoryNum;
}
