package service.item.impl;

import common.Constant;
import common.Result;
import mapper.ItemMapper;
import org.springframework.stereotype.Service;
import pojo.AppItemEntity;
import service.item.IItemService;
import service.item.vo.AppItem;
import util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/20 14:57
 */
@Service
public class ItemServiceImpl implements IItemService {
    @Resource
    private ItemMapper itemMapper;

    @Override
    public AppItemEntity findItemById(Integer appItemId) {
        return itemMapper.findItemById(appItemId);

    }

    @Override
    public Result addItem(AppItemEntity appItemEntity) {
        Result result = new Result();
        try {
            appItemEntity.setCreateTime(DateUtils.formatDate(new Date()));
            itemMapper.addItem(appItemEntity);
            //itemMapper.insert(appItemEntity);
            result.setFlag(Constant.SUCCESS).setMessage("商品信息保存成功");
        }catch (Exception exception){
            exception.printStackTrace();
            result.setFlag(Constant.FAIL).setMessage("商品信息保存失败!");
        }
        return result;
    }

    @Override
    public Result updateItem(AppItemEntity appItemEntity) {
        Result result = new Result();
        try {
            appItemEntity.setUpdateTime(DateUtils.formatDate(new Date()));
            itemMapper.updateItem(appItemEntity);
            result.setFlag(Constant.SUCCESS).setMessage("商品更新成功!");
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(Constant.FAIL).setMessage("商品更新失败!");
        }
        return result;
    }

    @Override
    public List<AppItem> findAllItem(AppItemEntity appItemEntity) {
        return itemMapper.findAllItem(appItemEntity);
    }

    @Override
    public Result changeState(Integer[] ids, Integer state) {
        Result result = new Result();
        try {
            for (Integer id : ids) {
                AppItemEntity item = itemMapper.findItemById(id);
                item.setIsShow(state);
                item.setUpdateTime(DateUtils.formatDate(new Date()));
                itemMapper.updateByPrimaryKeySelective(item);
                result.setFlag(Constant.SUCCESS).setMessage("商品已上架");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(Constant.FAIL).setMessage("商品已下架");
        }
        return result;
    }

    @Override
    public Result setHot(AppItemEntity appItemEntity) {
        Result result = new Result();
        try {
            appItemEntity.setItemCategoryId(17);
            itemMapper.updateByPrimaryKeySelective(appItemEntity);
            result.setFlag(Constant.SUCCESS).setMessage("设置爆品成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(Constant.FAIL).setMessage("设置爆品失败!");
        }
        return result;
    }

    @Override
    public Result delById(Integer[] ids) {
        Result result = new Result();
        try {
            for (Integer id : ids) {
                itemMapper.deleteByPrimaryKey(id);
                result.setFlag(Constant.SUCCESS).setMessage("商品删除成功!");
            }
        }catch (Exception e){
            result.setFlag(Constant.FAIL).setMessage("商品删除失败!");
            e.printStackTrace();
        }
        return result;
    }
}
