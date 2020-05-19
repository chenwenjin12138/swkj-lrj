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
 * @Date 2020/5/18 9:43
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "distributionrelation")
public class Distributionrelation extends Base{
    private static final long serialVersionUID = 6908187715119662419L;
    @Id
    private Integer userId;
    private Integer superId;
    private Byte type;
    private BigDecimal earnings;
}
