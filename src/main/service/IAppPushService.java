package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.AppPush;

/**
 * @author : fl
 * @describe : 消息推送
 * @date : 2020-5-11
 */
public interface IAppPushService {

    /**
     * 获取推送消息
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<AppPush> getMessagePageByParam(RequestDTO requestDTO);


    /**
     * 添加推送信息
     * @param push
     * @return
     */
    ReturnData<Boolean> addPush(AppPush push) ;

    /**
     * 删除推送信息
     * @param push
     * @return
     */
    ReturnData<Boolean> deletePush(AppPush push);


}
