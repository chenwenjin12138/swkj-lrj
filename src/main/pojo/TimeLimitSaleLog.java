package pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fl
 * @descrip: 价格修改记录
 * @date 2020/6/10 0010下午 2:20
 */
@Data
@TableName("limit_time_sale_log")
public class TimeLimitSaleLog {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer itemId;
    private BigDecimal before_price;
    private BigDecimal after_price;
    private LocalDateTime createTime;
}
