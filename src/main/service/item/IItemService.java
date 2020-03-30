package service.item;

import common.Result;
import pojo.AppItemEntity;
import service.item.vo.AppItem;

import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/20 14:57
 */
public interface IItemService {
    AppItemEntity findItemById(Integer appItemId);


    Result addItem(AppItemEntity appItemEntity);

    Result updateItem(AppItemEntity appItemEntity);


    List<AppItem> findAllItem(AppItemEntity appItemEntity);

    Result changeState(Integer[] ids, Integer state);

    Result setHot(AppItemEntity appItemEntity);

    Result delById(Integer[] ids);
}
