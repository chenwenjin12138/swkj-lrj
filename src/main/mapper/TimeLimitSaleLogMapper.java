package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pojo.AppItem;
import pojo.TimeLimitSaleLog;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Lxh
 * @date 2020/3/20 14:56
 */

public interface TimeLimitSaleLogMapper extends Mapper<TimeLimitSaleLog>, MySqlMapper<TimeLimitSaleLog>, BaseMapper<TimeLimitSaleLog> {
}
