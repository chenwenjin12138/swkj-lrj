package mapper;
import pojo.AppItem;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * @author Lxh
 * @date 2020/3/20 14:56
 */

public interface IItemMapper extends Mapper<AppItem>, MySqlMapper<AppItem> {
}
