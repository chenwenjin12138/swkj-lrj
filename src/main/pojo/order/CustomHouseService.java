package pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pojo.AppItem;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/2 11:03
 */
@Data
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_custom_house_service")
public class CustomHouseService implements Serializable {
    private static final long serialVersionUID = 8022401762178690722L;
    @Id
    private Byte id;
    /**订单号*/
    private String orderNumber;
    private String openTime;
    private String endTime;
    private Byte baseServiceCount;
    private BigDecimal baseServicePrice;
    /**定制详情*/
    private String individualServiceJson;
    /**服务周期*/
    private Integer serviceCycle;
    /**工作时间*/
    private String workTime;
    /**面积*/
    private String houseArea;

    @Transient
    private List<AppItem> itemList;

    public static final String COLUMN_ORDER_NUMBER = "orderNumber";
}
