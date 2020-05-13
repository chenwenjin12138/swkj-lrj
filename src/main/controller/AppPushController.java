package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pojo.AppPush;
import service.IAppPushService;

/**
 * @author : fl
 * @describe : 消息推送管理控制类
 * @date : 2020-5-13
 */
@RestController("/appPush")
@AllArgsConstructor
public class AppPushController {
    private IAppPushService appPushService;
    /**
     * 分页查询所有app用户
     * @return
     */
    @PostMapping("/appPushPageByParam")
    public PageInfo<AppPush> getMessagePageByParam(@RequestBody RequestDTO requestDTO){
       return appPushService.getMessagePageByParam(requestDTO);
    }

    /**
     * 删除消息
     * @return
     */
    @PostMapping("/deletePush")
    public ReturnData<Boolean> deletePush(@RequestBody AppPush push){
        return appPushService.deletePush(push);
    }

    /**
     * 保存推送消息
     * @return
     */
    @PostMapping("/addPush")
    public ReturnData<Boolean> addPush(@RequestBody AppPush push){
        return appPushService.addPush(push);
    }



}
