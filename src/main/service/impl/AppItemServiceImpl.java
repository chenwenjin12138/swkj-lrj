package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.AreaManagementMapper;
import mapper.IItemCatMapper;
import mapper.IItemMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.AppItem;
import pojo.AppItemCat;
import pojo.AreaManagement;
import pojo.Reservation;
import service.IAppItemService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import util.DateUtils;


import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @Resource
    private AreaManagementMapper areaManagementMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private ReturnData returnData = new ReturnData();

    /**
     * @Description: 商品分页查询
     * @Author: LxH
     * @Date: 2020/5/8 10:22
     */
    @Override
    public ReturnData<PageInfo<AppItem>> getAppItemPageByParam(RequestDTO requestDTO,AppItem appItem) {
        Example example = new Example(AppItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",1);
        if (appItem.getItemCategoryId()!=null) {
            criteria.andEqualTo("itemCategoryId",appItem.getItemCategoryId());
        }
        if (appItem.getItemName()!=null) {
            criteria.andLike("itemName","%"+appItem.getItemName()+"%");
        }
        int start = requestDTO.getPage() * requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start, requestDTO.getSize());
        int count = itemMapper.selectCount(new AppItem());
        System.out.println(count);
        List<AppItem> appItems = itemMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (AppItem item : appItems) {
            AppItemCat appItemCat = itemCatMapper.selectByPrimaryKey(item.getItemCategoryId());
            item.setItemCategoryName(appItemCat.getCategoryName());
        }
        ReturnData<PageInfo<AppItem>> returnData = new ReturnData<>();
        returnData.setData(new PageInfo<AppItem>(appItems,count)).setCode(SUCCESS_CODE).setMessage("查询成功");
        return returnData;
    }

    /**
     * @Description: 添加商品
     * @Author: LxH
     * @Date: 2020/5/8 10:39
     */
    @Transactional
    @Override
    public ReturnData<Boolean> addAppItem(AppItem item) {
        item.setIsShow(1).setActive(1).setCreateTime(DateUtils.formatDate(new Date()));
        if (itemMapper.insertSelective(item) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("商品添加成功!").setData(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("商品添加失败!").setData(false);
    }

    /**
     * @Description: 修改商品信息
     * @Author: LxH
     * @Date: 2020/5/8 10:58
     */
    @Transactional
    @Override
    public ReturnData<Boolean> updateAppItem(AppItem item) {
        item.setUpdateTime(DateUtils.formatDate(new Date()));
        if (itemMapper.updateByPrimaryKeySelective(item) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("商品修改成功!").setData(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("商品修改失败!").setData(false);
    }

    /**
     * @param: appItemIds
     * @Description: 根据条件删除用户
     * @Author: LxH
     * @Date: 2020/5/8 11:19
     */
    @Transactional
    @Override
    public ReturnData<Boolean> deleteAppItemById(Integer[] appItemIds) {
        for (Integer appItemId : appItemIds) {
            itemMapper.deleteByPrimaryKey(appItemId);
        }
        return returnData.setCode(SUCCESS_CODE).setMessage("商品删除成功").setData(null);
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
            return returnData.setCode(SUCCESS_CODE).setMessage("商品设置为爆品成功!").setData(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("商品设置为爆品失败!").setData(false);
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
            return returnData.setCode(SUCCESS_CODE).setMessage("查询成功").setData(appItem);
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
            return new ReturnData().setCode(Fail_CODE).setData(null);
        }
        return new ReturnData().setCode(SUCCESS_CODE).setData(appItem.getPicture());
    }

    @Override
    public ReturnData<List<AppItem>> getAppItemListByParam(RequestDTO requestDTO){
        QueryWrapper<AppItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq(AppItem.SHOW_COLUMN,1);
        if (requestDTO.getData() !=null ) {
            try {
                AppItem  appItem = objectMapper.convertValue(requestDTO.getData(), AppItem.class);
                if (appItem != null && appItem.getAppItemId()!= null) {
                    queryWrapper.eq(AppItem.ID_COLUMN,appItem.getAppItemId());
                }
                if (appItem != null && StringUtils.isNotEmpty(appItem.getBargainType())) {
                    queryWrapper.eq(AppItem.BARGAIN_TYPE_COLUMN,appItem.getBargainType());
                }
                return new ReturnData<>(SUCCESS_CODE,"操作成功",itemMapper.selectList(queryWrapper));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return new ReturnData<>(Fail_CODE, "操作失败", null);
            }
        }
        return new ReturnData<>(SUCCESS_CODE,"操作成功",itemMapper.selectList(queryWrapper));
    }
}
