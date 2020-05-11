package pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author fl
 * @Description: 余额类
 * @date 2020/4/30 0030下午 4:34
 */
@Data
public class Balance {

    @TableId("user_id")
    private Integer userId;
    /**
     * 用户余额
     */
    private BigDecimal balance;
    /**
     * 用户充值总额
     */
    private BigDecimal topUpAmount;
    /**
     * 用户消费总额
     */
    private BigDecimal expendAmount;
    /**
     * 余额最后变动时间
     */
    private String lastModifyTime = "";
    /**
     * 创建时间
     */
    private String createTime = "";

    public static final String COLUMN_USER_ID = "user_id";
}