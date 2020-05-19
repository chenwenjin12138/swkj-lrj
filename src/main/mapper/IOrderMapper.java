package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pojo.order.Order;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author : fl
 * @describe :订单管理的映射接口
 * @date : 2020-5-11
 */
public interface IOrderMapper extends BaseMapper<Order>,Mapper<Order>,MySqlMapper<Order> {

}
