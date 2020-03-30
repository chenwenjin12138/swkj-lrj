package service.item.impl;

import common.Constant;
import common.Result;
import mapper.ItemCatMapper;
import org.springframework.stereotype.Service;
import pojo.AppItemCategoryEntity;
import service.item.IItemCatService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/24 10:10
 */
@Service
public class ItemCatServiceImpl implements IItemCatService {
    @Resource
    private ItemCatMapper itemCatMapper;

    @Override
    public List<AppItemCategoryEntity> findAll() {
        return itemCatMapper.selectAll();
    }

    @Override
    public Result addItemCat(AppItemCategoryEntity appItemCategoryEntity) {
        int insert = itemCatMapper.insert(appItemCategoryEntity);
        Result result = new Result();
        if (insert==1) {
            result.setFlag(Constant.SUCCESS).setMessage("商品种类添加成功!");
        }else {
            result.setFlag(Constant.FAIL).setMessage("商品种类添加失败!");
        }
        return result;
    }

    @Override
    public Result updateItemCat(AppItemCategoryEntity appItemCategoryEntity) {
        Result result = new Result();
        int update = itemCatMapper.updateByPrimaryKeySelective(appItemCategoryEntity);
        if (update==1) {
            result.setFlag(Constant.SUCCESS).setMessage("商品种类更新成功!");
        }else {
            result.setFlag(Constant.FAIL).setMessage("商品种类更新失败!");
        }
        return result;
    }

}
