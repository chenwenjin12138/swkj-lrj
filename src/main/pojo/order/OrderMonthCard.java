package pojo.order;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fl
 * @descrip: 月卡订单
 * @date 2020/5/14 0014下午 3:30
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_month_card")
public class OrderMonthCard {
    @TableId
    @javax.persistence.Id
    @Column(name = "id")
    private Integer Id;
    /**
     * 订单号
     */
    private String orderNumber;
    private String userId;

    /**
     * 0 不能用 1 能用
     */
    private Integer active;
    /**
     * 月卡类型id
     */
    private String monthCardId;

    /**
     *月卡使用次数
     */
    private Integer userMonthCardCount;

    /**
     * 使用详情
     */
    private String userMonthCardItemJson;

    private String createTime;
    private String endTime;

    public static final String USER_ID_COLUMN = "user_id";
    public static final String COLUMN_ORDER_NUMBER = "orderNumber";
    public static final String COLUMN_MONTH_CARD_ID= "monthCardId";
}
