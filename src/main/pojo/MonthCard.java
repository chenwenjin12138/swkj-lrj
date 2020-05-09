package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

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
    private Integer id;

    /**下面这些认不得*/
    private Integer count;

    private String name;
    private BigDecimal price;
    private Integer expire;
    private Byte status;
    private Byte cardType;
    private Byte timeType;
    private String detail;
    private Float shareDicount;
}
