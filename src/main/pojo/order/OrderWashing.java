package pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Results;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description: 单项洗衣
 * @Author Lxh
 * @Date 2020/5/29 15:06
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_washing")
public class OrderWashing {
    @Id
    private Byte id;
    private String orderNumber;
    private String takeTime;
    private Integer takeConsigneeId;
    private Integer sendConsigneeId;
    private BigDecimal urgentPrice;
    private Integer isLock;
    private String shoppingJson;
    private BigDecimal servicePrice;

}
