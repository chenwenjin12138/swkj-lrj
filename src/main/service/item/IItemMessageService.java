package service.item;

import common.Result;
import service.item.vo.ItemMessage;

import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/24 11:24
 */
public interface IItemMessageService {
    List<ItemMessage> findAll();


    List<ItemMessage> getCategoryName();


    Result addMessage(ItemMessage itemMessage);
}
