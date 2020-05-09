package controller.item;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.AppItem;
import service.IAppItemService;

import javax.annotation.Resource;

/**
 * @Description: 后台管理系统商品模块
 * @Author Lxh
 * @Date 2020/5/8 9:14
 */
@RestController
public class AppItemController {
    @Resource
    private IAppItemService appItemService;

    /**
     * @Description: 商品分页查询
     * @Author: LxH
     * @Date: 2020/5/8 10:22
     */
    @RequestMapping("/findAppItemPageByParam")
    public PageInfo<AppItem> getAppItem(RequestDTO requestDTO){
        return appItemService.getAppItemPageByParam(requestDTO);
    }

    /**
     * @Description: 添加商品
     * @Author: LxH
     * @Date: 2020/5/8 10:39
     */
    @RequestMapping("/addAppItem")
    public ReturnData<Boolean> addAppItem(AppItem item){
        return appItemService.addAppItem(item);
    }

    /**
     * @Description: 修改商品信息
     * @Author: LxH
     * @Date: 2020/5/8 10:57
     */
    @RequestMapping("/updateAppItem")
    public ReturnData<Boolean> updateAppItem(AppItem item){
        return appItemService.updateAppItem(item);
    }

    /**
     * @Description: 根据id删除商品信息
     * @Author: LxH
     * @Date: 2020/5/8 11:18
     */
    @RequestMapping("/deleteAppItem")
    public ReturnData<Boolean> deleteAppItem(Integer [] appItemIds){
        return appItemService.deleteAppItemById(appItemIds);
    }

    /**
     * @Description: 设置为爆品
     * @Author: LxH
     * @Date: 2020/5/8 14:25
     */
    @RequestMapping("/setExplosives")
    public ReturnData<Boolean> setExplosives(Integer appItemId){
        return appItemService.setExplosives(appItemId);
    }

    /**
     * @Description: 条件查询
     * @Author: LxH
     * @Date: 2020/5/8 18:08
     */
    @RequestMapping("/findAppItemByIds")
    public ReturnData findAppItemByIds(Integer appItemId,Integer itemCategoryId){
        return appItemService.findAppItemByIds(appItemId,itemCategoryId);
    }

    /**
     * @Description: 查看图片
     * @Author: LxH
     * @Date: 2020/5/8 19:06
     */
    @RequestMapping("/findImageById")
    public ReturnData findImageById(Integer appItemId){
        return appItemService.findImageById(appItemId);
    }
}
