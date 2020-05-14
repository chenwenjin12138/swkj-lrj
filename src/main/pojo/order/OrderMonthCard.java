package pojo.order;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author fl
 * @descrip: 月卡订单
 * @date 2020/5/14 0014下午 3:30
 */
@Data
public class OrderMonthCard {
    @TableId
    private Integer Id;
    /**
     * 订单号
     */
    private String orderNumber;
    private String userId;
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
}
