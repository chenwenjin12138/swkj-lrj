package controller.item;

import common.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.AppItemEntity;
import service.item.IItemService;
import service.item.vo.AppItem;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/20 14:28
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource
    private IItemService itemService;


    /**
     * <b>initAdd</b>：(跳转商品信息新增/item/add。jsp页面)<br>
     * <b>url</b>：(basePath../item/init-add)<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    @RequestMapping(value = "/init-add", method = RequestMethod.GET)
    public String initAdd() {
        System.out.println("进入请求:item/init-add");

        return "item/add";
    }

    /**
     * <b>initAdd</b>：(跳转商品信息编辑/item/edit。jsp页面)<br>
     * <b>url</b>：(basePath../item/init-edit)<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    @RequestMapping(value = "/init-edit", method = RequestMethod.GET)
    public String initEdit() {
        System.out.println("以跳转");
        return "/item/edit";
    }

    /**
     * <b>initAdd</b>：(跳转商品信息列表/item/list.jsp页面)<br>
     * <b>url</b>：(basePath../item/init-list)<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    @RequestMapping(value = "/init-list", method = RequestMethod.GET)
    public String initList() {

        return "/item/list";
    }
    /**
     *
     * @功能说明:跳转新增爆品页面
     * @return
     * @返回类型:String
     * @方法名称:initAddHot
     * @类名称:ItemController
     * @文件名称:ItemController.java
     * @作者:lxh
     * @版本:1.0
     */
    @RequestMapping(value = "/init-addHot", method = RequestMethod.GET)
    public String initAddHot(){
        return "/item/addHot";
    }
    /**
     * findItemById(此方法根据客户端提交后台产品ID,根据ID查询该商品的实体对象,并转换成JSON串返回。)
     * TODO(需通过ajaxGET请求该接口)
     * url(basePath../item/find)
     * @param appItemId
     *            (商品ID)
     * @return AppItemEntity(商品对象)
     * @Exception Exception
     * @author lxh
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public AppItemEntity findItemById(@Param("appItemId") Integer appItemId){
        return itemService.findItemById(appItemId);
    }
    /**
     * <b>addItem</b>：(根据客户端提交商品信息对象,将其持久化到数据库)<br>
     * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
     * <b>url</b>：(basePath.../item/add)<br>
     * @param appItemEntity
     *            商品信息对象实体<br>
     * @return Result<br>
     * @Exception<br>
     * @author lxh
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addItem(AppItemEntity appItemEntity){

        return itemService.addItem(appItemEntity);
    }
    /**
     * <b>updateItem</b>：(根据客户端提交商品信息对象,将其更新到数据库)<br>
     * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
     * <b>url</b>：(basePath.../item/edit)<br>
     * @param appItemEntity
     *            商品信息对象实体<br>
     * @return Result<br>
     * @Exception<br>
     * @author lxh
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result updateItem(AppItemEntity appItemEntity){

        return itemService.updateItem(appItemEntity);
    }
    /**
     * <b>findAllItem</b>：(根据条件查询所有商品信息对象)<br>
     * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
     * <b>url</b>：(basePath../item/list)<br>
     * @param appItemEntity
     *            商品信息对象实体<br>
     * @return List<AppItem><br>
     * @Exception<br>
     * @author lxh
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<AppItem> findAllItem(AppItemEntity appItemEntity){
        return itemService.findAllItem(appItemEntity);
    }
    /**
     * <b>changeState</b>：(见父类方法)<br>
     * <b>TODO</b>：(需通过ajaxGET请求该接口)<br>
     * <b>url</b>：(basePath../item/changeState)<br>
     * @param IDS 编号集合<br>
     * @param state 状态码<br>
     * @return Result<br>
     * @Exception<br>
     * @author lxh
     */
    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
    @ResponseBody
    public Result changeState(@RequestParam("ids") Integer[] ids ,@RequestParam("state") Integer state){
        return itemService.changeState(ids,state);
    }
    /**
     *
     * @功能说明:
     * @return
     * @返回类型:Result
     * @方法名称:setToHot
     * @类名称:ItemController
     * @文件名称:ItemController.java
     * @所属包名:com.lanrenxiyi.web.controller
     * @项目名称:lanrenxiyi
     * 上午11:32:09
     * @作者:lxh
     * @版本:1.0
     */
    @RequestMapping(value = "/setToHot", method = RequestMethod.POST)
    @ResponseBody
    public Result setHot(AppItemEntity appItemEntity){
        return itemService.setHot(appItemEntity);
    }

    /**
     *
     * @功能说明:根据id删除记录
     * @param appItemId
     * @return
     * @返回类型:Result
     * @方法名称:delById
     * @类名称:ItemController
     * @文件名称:ItemController.java
     *
     * @项目名称:lanrenxiyi
     * @创建时间:2017-7-31 下午3:17:44
     * @作者:lxh
     * @版本:1.0
     */
    @RequestMapping(value = "/delById", method = RequestMethod.POST)
    @ResponseBody
    public Result delById(@RequestParam("ids") Integer [] ids){
        return itemService.delById(ids);
    }

}