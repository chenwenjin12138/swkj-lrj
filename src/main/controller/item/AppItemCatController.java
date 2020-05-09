package controller.item;

import dto.ReturnData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.AppItemCat;
import pojo.Balance;
import service.IAppItemCatService;
import vo.Node;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/8 16:07
 */
@RestController
public class AppItemCatController {

    @Resource
    private IAppItemCatService appItemCatService;
    
    /**
     * @Description: tree节点信息查询
     * @Author: LxH
     * @Date: 2020/5/8 16:13
     */
    @RequestMapping("/findTreeNodes")
    public List<Node> findTreeNodes(){
        return appItemCatService.findTreeNodes();
    }

    /**
     * @Description: 获取全部商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:21
     */
    @RequestMapping("/findAllItemCats")
    public ReturnData<List<AppItemCat>> findAllItemCats(){
        return appItemCatService.findAllItemCats();
    }

    /**
     * @Description: 添加商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:52
     */
    @RequestMapping("/addAppItemCat")
    public ReturnData<Balance> addAppItemCat(AppItemCat appItemCat){
        return appItemCatService.addAppItemCat(appItemCat);
    }
}
