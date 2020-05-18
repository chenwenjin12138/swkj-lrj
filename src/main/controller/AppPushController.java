package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.AppPush;
import service.IAppPushService;

/**
 * @author : fl
 * @describe : 消息推送管理控制类
 * @date : 2020-5-13
 */
@RestController
@RequestMapping("/appPush")
@AllArgsConstructor
@Api(tags = "消息推送")
public class AppPushController {
    private IAppPushService appPushService;
    /**
     * 分页查询所有推送消息
     * @return
     */
    @PostMapping("/appPushPageByParam")
    @ApiOperation(value = "分页查询消息推送")
    public PageInfo<AppPush> getMessagePageByParam(@RequestBody RequestDTO requestDTO){
       return appPushService.getMessagePageByParam(requestDTO);
    }

    /**
     * 删除消息
     * @return
     */
    @PostMapping("/deletePush")
    @ApiOperation(value = "消息推送删除")
    public ReturnData<Boolean> deletePush(@RequestBody AppPush push){
        return appPushService.deletePush(push);
    }

    /**
     * 保存推送消息
     * @return
     */
    @PostMapping("/addPush")
    @ApiOperation(value = "新增推送消息")
    public ReturnData<Boolean> addPush(@RequestBody AppPush push){
        return appPushService.addPush(push);
    }



}
