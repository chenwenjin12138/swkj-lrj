package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/1 15:30
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_house_service")
public class OrderHouseService {
    @Id
    private Integer id;
    private String orderNumber;
    private Byte status;
    private Integer itemId;
    private Integer takeConsigneeId;
}
