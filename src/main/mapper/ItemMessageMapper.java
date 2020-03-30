package mapper;

import service.item.vo.ItemMessage;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/24 11:23
 */
public interface ItemMessageMapper extends Mapper<ItemMessage>, MySqlMapper<ItemMessage> {
    List<ItemMessage> findAll();

    List<ItemMessage> getCategoryName();

    void addMessssage(ItemMessage setCreateTime);
}
