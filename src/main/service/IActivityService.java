package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.AppFeedback;
import pojo.activity.Activity;
import pojo.activity.ActivityVo;

/**
 * @author : fl
 * @describe :活动
 * @date : 2020-5-11
 */
public interface IActivityService {

    /**
     * 查询
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<ActivityVo> getPageByParam(RequestDTO requestDTO);


    /**
     * 添加活动
     * @param activityVo
     * @return
     */
    ReturnData<Boolean> add(ActivityVo activityVo) ;

    /**
     * 修改
     * @param activity
     * @return
     */
    ReturnData<Boolean> update(Activity activity) ;


    /**
     * 删除
     * @param id
     * @return
     */
    ReturnData<Boolean> delete(int id);


}
