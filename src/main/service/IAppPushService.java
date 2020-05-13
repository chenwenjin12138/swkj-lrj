package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.AppPush;
import pojo.Order;

/**
 * @author : fl
 * @describe : 消息推送
 * @date : 2020-5-11
 */
public interface IAppPushService {

    /**
     * 根据条件订单信息
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<AppPush> getMessagePageByParam(RequestDTO requestDTO);


    /**
     * 添加订单信息
     * @param push
     * @return
     */
    ReturnData<Boolean> addPush(AppPush push) throws Exception;

}
