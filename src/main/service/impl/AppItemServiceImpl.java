package service.impl;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.IItemCatMapper;
import mapper.IItemMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import pojo.AppItem;
import pojo.AppItemCat;
import service.IAppItemService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/8 9:24
 */
@Service
public class AppItemServiceImpl implements IAppItemService {

    @Resource
    private IItemMapper itemMapper;

    @Resource
    private IItemCatMapper itemCatMapper;

    private ReturnData returnData = new ReturnData();

    /**
     * @Description: 商品分页查询
     * @Author: LxH
     * @Date: 2020/5/8 10:22
     */
    @Override
    public PageInfo<AppItem> getAppItemPageByParam(RequestDTO requestDTO) {
        Example example = new Example(AppItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",1);
        int start = requestDTO.getPage() * requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start, requestDTO.getSize());
        List<AppItem> appItems = itemMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (AppItem appItem : appItems) {
            AppItemCat appItemCat = itemCatMapper.selectByPrimaryKey(appItem.getAppItemId());
            appItem.setItemCategoryName(appItemCat.getCategoryName());
        }
        return new PageInfo<AppItem>(appItems);
    }

    /**
     * @Description: 添加商品
     * @Author: LxH
     * @Date: 2020/5/8 10:39
     */
    @Override
    public ReturnData<Boolean> addAppItem(AppItem item) {
        item.setIsShow(1).setCreateTime(DateUtils.formatDate(new Date()));
        if (itemMapper.insertSelective(item) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("商品添加成功!").setObject(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("商品添加失败!").setObject(false);
    }

    /**
     * @Description: 修改商品信息
     * @Author: LxH
     * @Date: 2020/5/8 10:58
     */
    @Override
    public ReturnData<Boolean> updateAppItem(AppItem item) {
        item.setUpdateTime(DateUtils.formatDate(new Date()));
        if (itemMapper.updateByPrimaryKeySelective(item) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("商品修改成功!").setObject(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("商品修改失败!").setObject(false);
    }

    /**
     * @param: appItemIds
     * @Description: 根据条件删除用户
     * @Author: LxH
     * @Date: 2020/5/8 11:19
     */
    @Override
    public ReturnData<Boolean> deleteAppItemById(Integer[] appItemIds) {
        for (Integer appItemId : appItemIds) {
            itemMapper.deleteByPrimaryKey(appItemId);
        }
        return returnData.setCode(SUCCESS_CODE).setMessage("商品删除成功").setObject(null);
    }

    /**
     * @param: appItemId
     * @Description: 设置为爆品
     * @Author: LxH
     * @Date: 2020/5/8 14:26
     */
    @Override
    public ReturnData<Boolean> setExplosives(Integer appItemId) {
        AppItem appItem = itemMapper.selectByPrimaryKey(appItemId);
        if (itemMapper.updateByPrimaryKeySelective(appItem.setItemCategoryId(17)) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("商品设置为爆品成功!").setObject(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("商品设置为爆品失败!").setObject(false);
    }

    /**
     * @param: appItemId
     * @param: itemCategoryId
     * @Description: 条件查询
     * @Author: LxH
     * @Date: 2020/5/8 18:08
     */
    @Override
    public ReturnData findAppItemByIds(Integer appItemId, Integer itemCategoryId) {
        Example example = new Example(AppItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("appItemId",appItemId).andEqualTo("itemCategoryId",itemCategoryId);
        List<AppItem> appItems = itemMapper.selectByExample(example);
        for (AppItem appItem : appItems) {
            return returnData.setCode(SUCCESS_CODE).setMessage("查询成功").setObject(appItem);
        }
        return null;
    }

    /**
     * @param: appItemId
     * @Description: 查看图片
     * @Author: LxH
     * @Date: 2020/5/8 19:07
     */
    @Override
    public ReturnData findImageById(Integer appItemId) {
        AppItem appItem = itemMapper.selectByPrimaryKey(appItemId);
        if (appItem == null) {
            return new ReturnData().setCode(Fail_CODE).setObject(null);
        }
        return new ReturnData().setCode(SUCCESS_CODE).setObject(appItem.getPicture());
    }
}
