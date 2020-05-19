package controller.item;

import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.AppItemCat;
import service.IAppItemCatService;
import vo.Node;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 后台管理系统商品种类模块
 * @Author Lxh
 * @Date 2020/5/8 16:07
 */
@RestController
@RequestMapping("itemCat")
@AllArgsConstructor
@Api(tags = "商品种类管理")
public class AppItemCatController {

    @Resource
    private IAppItemCatService appItemCatService;
    
    /**
     * @Description: tree节点信息查询
     * @Author: LxH
     * @Date: 2020/5/8 16:13
     */
    @ApiOperation(value = "tree节点信息查询")
    @RequestMapping("/findTreeNodes")
    public List<Node> findTreeNodes(){
        return appItemCatService.findTreeNodes();
    }

    /**
     * @Description: 获取全部商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:21
     */
    @ApiOperation(value = "获取全部商品种类信息")
    @RequestMapping("/findAllItemCats")
    public ReturnData<List<AppItemCat>> findAllItemCats(){
        return appItemCatService.findAllItemCats();
    }

    /**
     * @Description: 添加商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:52
     */
    @ApiOperation(value = "添加商品种类信息")
    @RequestMapping("/addAppItemCat")
    public ReturnData<Boolean> addAppItemCat(AppItemCat appItemCat){
        return appItemCatService.addAppItemCat(appItemCat);
    }
}
