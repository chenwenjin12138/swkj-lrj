package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pojo.order.Order;
import pojo.order.OrderMonthCard;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author : fl
 * @describe :月卡订单管理的映射接口
 * @date : 2020-5-11
 */
public interface OrderMonthCardMapper extends BaseMapper<OrderMonthCard> , Mapper<OrderMonthCard>, MySqlMapper<OrderMonthCard> {

}
