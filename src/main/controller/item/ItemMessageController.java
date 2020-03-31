package controller.item;

import common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.item.vo.ItemMessage;
import service.item.IItemMessageService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lxh
 * @date 2020/3/24 11:21
 */
@Controller
//@RequestMapping("/item-message")
public class ItemMessageController {
    @Resource
    private IItemMessageService itemMessageService;
    /**
     * <b>initAdd</b>：(跳转商品公告列表)<br>
     * <b>url</b>：(basePath../item-message/init-list)<br>
     *
     * @return String<br>
     * @Exception<br>
     * @author WR
     */
    @RequestMapping(value = "/initlist", method = RequestMethod.GET)
    public String initList() {

        return "/item-message/list";
    }

    /**
     * <b>initAdd</b>：(跳转商品公告新增/item-message/add.jsp页面)<br>
     * <b>url</b>：(basePath../item-message/init-add)<br>
     * @return String<br>
     * @Exception<br>
     * @author WR
     */

    @RequestMapping(value = "/initadd", method = RequestMethod.GET)
    public String initAdd() {

        return "/item-message/add";
    }

    /**
     * <b>initEdit</b>：(跳转商品信息编辑/item/edit。jsp页面)<br>
     * <b>url</b>：(basePath../item/init-edit)<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    @RequestMapping(value = "/initedit", method = RequestMethod.GET)
    public String initEdit() {

        return "/item-message/edit";
    }
    /**
     * <b>list</b>：(查询所有商品公告对象)<br>
     * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
     * <b>url</b>：(basePath../item-message/list)<br>
     * @return List<Itemmessage><br>
     * @return
     * @throws Exception
     * @author WR
     */
    @RequestMapping(value = "/meslist", method = RequestMethod.POST)
    @ResponseBody
    public List<ItemMessage> findAll(){
        return itemMessageService.findAll();
    }
/**
 * <b>list</b>：(查询所有商品种类和对应id)<br>
 * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
 * <b>url</b>：(basePath../item-message/getCategoryName)<br>
 * @return List<Itemmessage><br>
 * @return
 * @throws Exception
 * @author WR
 */
    @RequestMapping(value="/getCategoryName",method = RequestMethod.POST)
    @ResponseBody
    public List<ItemMessage> getCategoryName(){
        return itemMessageService.getCategoryName();
    }
/**
 * <b>list</b>：(添加商品公告对象)<br>
 * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
 * <b>url</b>：(basePath../item-message/add)<br>
 * @return List<Itemmessage><br>
 * @param imsg
 * @return
 */
    @RequestMapping(value="/sys/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addMessage(ItemMessage itemMessage){
        return itemMessageService.addMessage(itemMessage);
    }



}
