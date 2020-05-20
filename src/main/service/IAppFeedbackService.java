package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.AppFeedback;
import pojo.AppPush;

/**
 * @author : fl
 * @describe :用户反馈
 * @date : 2020-5-11
 */
public interface IAppFeedbackService {

    /**
     * 获取反馈消息
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<AppFeedback> getPageByParam(RequestDTO requestDTO);


    /**
     * 添加
     * @param feedback
     * @return
     */
    ReturnData<Boolean> add(AppFeedback feedback) ;


    /**
     * 删除
     * @param id
     * @return
     */
    ReturnData<Boolean> delete(int id);


}
