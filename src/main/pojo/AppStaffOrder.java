package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.math.BigInteger;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/16 14:28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_staff_order")
public class AppStaffOrder extends Base{
    private static final long serialVersionUID = -7605293492550297185L;
    private Integer staffId;
    private BigInteger orderId;
    private Byte isBind;
    private Byte shippingStatus;
    private Integer notifyCount;
}
