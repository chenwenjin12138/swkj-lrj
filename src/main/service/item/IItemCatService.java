package service.item;

import common.Result;
import pojo.AppItemCategoryEntity;

import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/24 10:05
 */
public interface IItemCatService {
    List<AppItemCategoryEntity> findAll();

    Result addItemCat(AppItemCategoryEntity appItemCategoryEntity);

    Result updateItemCat(AppItemCategoryEntity appItemCategoryEntity);
}
