package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.security.DenyAll;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/1 14:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemJson {
    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
}
