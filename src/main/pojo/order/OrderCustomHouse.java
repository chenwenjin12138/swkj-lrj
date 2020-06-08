package pojo.order;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fl
 * @descrip: 定制家政月卡订单
 * @date 2020/5/14 0014下午 3:30
 */
@Data
@TableName("order_custom_house_service")
public class OrderCustomHouse {
    @TableId
    private Integer Id;
    /**
     * 订单号
     */
    private String orderNumber;
    private Integer active;
    private LocalDateTime openTime;
    private LocalDateTime endTime;
    private Integer base_service_count;
    private BigDecimal base_service_price;
    private String individual_service_json;
    private Integer serviceCycle;
    private Integer workTime;
    private String house_area;

    public static final String USER_ID_COLUMN = "user_id";
}
