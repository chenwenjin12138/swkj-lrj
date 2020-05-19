package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.AppPush;
import pojo.ValueAddedServices;
import service.IAppPushService;
import service.IValueAddServicesService;

import java.util.List;

/**
 * @author : fl
 * @describe : 增值服务管理控制类
 * @date : 2020-5-13
 */
@RestController
@RequestMapping("/valueAddService")
@AllArgsConstructor
@Api(tags = "增值服务管理")
public class ValueAddServiceController {
    private IValueAddServicesService valueAddServicesService;

    /**
     * 查找增值服务
     * @param requestDTO 查询条件
     * @return
     */
    @GetMapping("/getListByParam")
    @ApiOperation(value = "查询增值服务",notes = "不传参数")
   public List<ValueAddedServices> getListByParam(RequestDTO requestDTO){
        return valueAddServicesService.getListByParam(requestDTO);
    }

    /**
     * 添加
     * @param valueAddedServices
     * @return
     */

    @PostMapping("/add")
    @ApiOperation(value = "增加增值服务")
    public ReturnData<Boolean> add(ValueAddedServices valueAddedServices){
        return  valueAddServicesService.add(valueAddedServices);
    }

    /**
     * 修改
     * @param valueAddedServices
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改增值服务")
    public ReturnData<Boolean> update(ValueAddedServices valueAddedServices){
        return valueAddServicesService.update(valueAddedServices);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除增值服务")
    public ReturnData<Boolean> delete(@PathVariable int id){
        return valueAddServicesService.delete(id);
    }


}
