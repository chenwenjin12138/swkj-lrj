package controller.item;

import common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.AppItemCategoryEntity;
import service.item.IItemCatService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/24 10:03
 */
@Controller
@RequestMapping("/item-category")
public class ItemCatController {
    @Resource
    private IItemCatService itemCatService;
    /**
     * <b>initList</b>：(跳转商品分类管理列表/item-category/list.jsp页面)<br>
     * <b>url</b>：(basePath../item-category/init-list)<br>
     * @param<br>
     * @param<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    @RequestMapping(value = "/init-list")
    public String initList() {

        return "/item-category/list";
    }
    /**
     * <b>list</b>：(此方法返回所有商品类别信息,转换成json数组。)<br>
     * <b>TODO</b>：(需通过ajaxGET请求该接口)<br>
     * <b>url</b>：(basePath../item-category/list)<br>
     * @return List<AppItemCategoryEntity> list 接口存储 AppItemCategoryEntity对象<br>
     * @Exception<br>
     * @author lxh
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<AppItemCategoryEntity> findAll() {
        return itemCatService.findAll();
    }
    /**
     * <b>add</b>：(此方法根据客户端提交商品分类对象，将其持久化到数据库。)<br>
     * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
     * <b>url</b>：(basePath../item-category/add)<br>
     * @param appItemCategoryEntity
     *            商品分类实体对象<br>
     * @return Result 见注释<br>
     * @Exception<br>
     * @author lxh
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addItemCat(AppItemCategoryEntity appItemCategoryEntity){
        return itemCatService.addItemCat(appItemCategoryEntity);
    }
    /**
     * <b>edit</b>：(此方法根据客户端提交更新过的商品分类实体对象,将其更新到数据库中。)<br>
     * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
     * <b>url</b>：(basePath..)<br>
     * @param appItemCategoryEntity
     *            商品分类实体对象<br>
     * @return Result见注释<br>
     * @Exception<br>
     * @author lxh
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result updateItemCat(AppItemCategoryEntity appItemCategoryEntity){
        return itemCatService.updateItemCat(appItemCategoryEntity);
    }

}
