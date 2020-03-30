package mapper;


import org.apache.ibatis.annotations.Select;
import pojo.AppItemEntity;
import service.item.vo.AppItem;

import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/20 14:56
 */

public interface ItemMapper extends tk.mybatis.mapper.common.Mapper<AppItemEntity>, tk.mybatis.mapper.common.MySqlMapper<AppItemEntity> {
    @Select("select * from app_item where app_item_id = #{appItemId}")
    AppItemEntity findItemById(Integer appItemId);

    void addItem(AppItemEntity appItemEntity);

    void updateItem(AppItemEntity appItemEntity);

    List<AppItem> findAllItem(AppItemEntity appItemEntity);
}
