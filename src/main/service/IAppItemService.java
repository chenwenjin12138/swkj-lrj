package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.AppItem;

import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/8 9:23
 */
public interface IAppItemService {
    
    /**
     * @Description: 商品分页查询
     * @Author: LxH
     * @Date: 2020/5/8 9:58
     */
    ReturnData<PageInfo<AppItem>> getAppItemPageByParam(RequestDTO requestDTO,AppItem appItem);

    /**
     * @Description: 添加商品
     * @Author: LxH
     * @Date: 2020/5/8 10:39
     */
    ReturnData<Boolean> addAppItem(AppItem item);

    /**
     * @Description: 修改商品信息
     * @Author: LxH
     * @Date: 2020/5/8 10:58
     */
    ReturnData<Boolean> updateAppItem(AppItem item);

    /**
     * @Description: 根据条件删除用户
     * @Author: LxH
     * @Date: 2020/5/8 11:19
     */
    ReturnData<Boolean> deleteAppItemById(Integer[] appItemIds);

    /**
     * @Description: 设置为爆品
     * @Author: LxH
     * @Date: 2020/5/8 14:26
     */
    ReturnData<Boolean> setExplosives(Integer appItemId);

    /**
     * @Description: 条件查询
     * @Author: LxH
     * @Date: 2020/5/8 18:08
     */
    ReturnData findAppItemByIds(Integer appItemId, Integer itemCategoryId);

    /**
     * @Description: 查看图片
     * @Author: LxH
     * @Date: 2020/5/8 19:07
     */
    ReturnData findImageById(Integer appItemId);

    /**
     * @Description: 商品列表查询
     * @Author: LxH
     * @Date: 2020/5/8 9:58
     */
    List<AppItem> getAppItemListByParam(RequestDTO requestDTO);


}
