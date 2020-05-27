package controller.item;

import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.AreaManagement;
import service.AreaManagementService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/27 15:10
 */
@RequestMapping("area")
@RestController
@Api(tags = "家政面积管理")
@AllArgsConstructor
public class AreaManagementController {

    @Resource
    private AreaManagementService areaManagementService;

    /**
     * @Description: 根据商品查询面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:31
     */
    @RequestMapping("findAreaByItemId")
    @ApiOperation(value = "根据商品查询面积信息")
    public List<AreaManagement> findAreaByItemId(Integer itemId){
        return areaManagementService.findAreaByItemId(itemId);
    }

    /**
     * @Description: 根据商品添加面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:39
     */
    @RequestMapping("addAreaByItemId")
    @ApiOperation(value = "根据面积添加面积信息")
    public ReturnData addAreaByItemId(AreaManagement areaManagement){
        return areaManagementService.addAreaByItemId(areaManagement);
    }

    /**
     * @Description: 根据商品修改面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:51
     */
    @RequestMapping("updateAreaById")
    @ApiOperation(value = "根据面积修改面积信息")
    public ReturnData updateAreaById(AreaManagement areaManagement){
        return areaManagementService.updateAreaByItemId(areaManagement);
    }

    /**
     * @Description: 根据面积主键删除面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:59
     */
    @RequestMapping("deleteAreaByItemIds")
    @ApiOperation(value = "根据面积主键删除面积信息")
    public ReturnData deleteAreaByItemIds(@RequestParam("id[]") Integer[] areaManagementIds){
        return areaManagementService.deleteAreaByItemIds(areaManagementIds);
    }

}
