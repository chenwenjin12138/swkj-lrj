package controller.item;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
@RequestMapping("item")
@AllArgsConstructor
@Api(tags = "商品管理")
public class AppItemController {

    @Resource
    private IAppItemService appItemService;

    /**
     * @Description: 商品分页查询
     * @Author: LxH
     * @Date: 2020/5/8 10:22
     */
    @ApiOperation(value = "商品分页查询")
    @RequestMapping("/findAppItemPageByParam")
    public PageInfo<AppItem> getAppItem(RequestDTO requestDTO,AppItem appItem){
        return appItemService.getAppItemPageByParam(requestDTO,appItem);
    }

    /**
     * @Description: 添加商品
     * @Author: LxH
     * @Date: 2020/5/8 10:39
     */
    @ApiOperation(value = "添加商品")
    @RequestMapping("/addAppItem")
    public ReturnData<Boolean> addAppItem(AppItem item){
        return appItemService.addAppItem(item);
    }

    /**
     * @Description: 修改商品信息
     * @Author: LxH
     * @Date: 2020/5/8 10:57
     */
    @ApiOperation(value = "修改商品")
    @RequestMapping("/updateAppItem")
    public ReturnData<Boolean> updateAppItem(AppItem item){
        return appItemService.updateAppItem(item);
    }

    /**
     * @Description: 根据id删除商品信息
     * @Author: LxH
     * @Date: 2020/5/8 11:18
     */
    @ApiOperation(value = "根据id删除商品信息")
    @RequestMapping("/deleteAppItem")
    public ReturnData<Boolean> deleteAppItem(Integer [] appItemIds){
        return appItemService.deleteAppItemById(appItemIds);
    }

    /**
     * @Description: 设置为爆品
     * @Author: LxH
     * @Date: 2020/5/8 14:25
     */
    @ApiOperation(value = "设置为爆品")
    @RequestMapping("/setExplosives")
    public ReturnData<Boolean> setExplosives(Integer appItemId){
        return appItemService.setExplosives(appItemId);
    }

    /**
     * @Description: 条件查询
     * @Author: LxH
     * @Date: 2020/5/8 18:08
     */
   /* @ApiOperation(value = "条件查询")
    @RequestMapping("/findAppItemByIds")
    public ReturnData findAppItemByIds(Integer appItemId,Integer itemCategoryId){
        return appItemService.findAppItemByIds(appItemId,itemCategoryId);
    }*/

    /**
     * @Description: 查看图片
     * @Author: LxH
     * @Date: 2020/5/8 19:06
     */
    @ApiOperation(value = "查看图片")
    @RequestMapping("/findImageById")
    public ReturnData findImageById(Integer appItemId){
        return appItemService.findImageById(appItemId);
    }
}
