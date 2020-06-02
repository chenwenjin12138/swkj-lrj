package pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Results;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Integer isUrgent;
    private Integer isLock;
    @Column(name = "shopping_JSON")
    private String shoppingJSON;
}
