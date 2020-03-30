package mapper;

import pojo.AppItemCategoryEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Lxh
 * @date 2020/3/24 10:09
 */
public interface ItemCatMapper extends Mapper<AppItemCategoryEntity>, MySqlMapper<AppItemCategoryEntity> {
}
