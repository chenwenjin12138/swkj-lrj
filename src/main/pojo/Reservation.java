package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

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
    private BigDecimal totalPrice;
    private Integer userId;
    private String orderNumber;
    private String longitude;
    private String latitude;
    private String reservationJson;
    private Integer isUrgent;
    private Integer isService;
    private Byte isShare;
    private Byte isEnd;
    //private Byte payStatus;
    /**抢单时间*/
    private String takeOrderTime;
    /**取衣时间*/
    private String getClothesTime;
    /**实际取衣时间*/
    private String reGetClothesTime;
    /**送回时间*/
    private String sendBackTime;

    /**
     * 数据库字段名
     */
    public static final String COLUMN_ORDER_TYPE = "orderType";
    public static final Integer TYPE_LAUNDRY = 1;
    public static final Integer TYPE_MONTH_CARD = 2;
    public static final Integer TYPE_HOUSEKEEPING = 3;
    public static final Integer TYPE_CUSTOM = 4;
    public static final String COLUMN_CREATE_TIME = "createTime";
    public static final String COLUMN_CREATE_TIME1 = "create_time";

    /**订单追踪状态**/
    //小哥上门收件中
    public static final int ORDER_TRANSSTATUS_TAKEING =1;
    public static final int ORDER_TRANSSTATUS_TAKED = 2; //已取衣
    public static final int ORDER_TRANSSTATUS_BINDING = 3;//订单绑定成功
    public static final int ORDER_TRANSSTATUS_GO = 4;//衣物飞奔入场
    public static final int ORDER_TRANSSTATUS_SORTING = 5;//衣物分拣中
    public static final int ORDER_TRANSSTATUS_WASHING = 6;//衣服洗涤中
    public static final int ORDER_TRANSSTATUS_STERILIZED = 7;//衣物烘干消毒
    public static final int ORDER_TRANSSTATUS_IRONING = 8;//衣服熨烫完毕
    public static final int ORDER_TRANSSTATUS_TAKED_PACK = 9;//衣物打包
    public static final int ORDER_TRANSSTATUS_RECEIVEING = 10; //正在送回
    public static final int ORDER_TRANSSTATUS_RECEIVED = 11;//已送回
}
