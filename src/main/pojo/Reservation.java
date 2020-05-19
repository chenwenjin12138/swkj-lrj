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
 * @Date 2020/5/14 16:48
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation extends Base{
    private static final long serialVersionUID = -6654506146283156307L;
    @Id
    private Integer reservationId;
    private Byte status;
    private Byte trackingStatus;
    private Integer orderType;
    private Integer grabOrderId;
    private String address;
    private Integer userId;
    private String orderNumber;
    private String longitude;
    private String latitude;
}
