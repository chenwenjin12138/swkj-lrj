package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/1 15:13
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "balance")
public class BalanceVo implements Serializable {
    private static final long serialVersionUID = 505862135251582699L;
    @Id
    private Integer userId;
    private BigDecimal balance;
}
