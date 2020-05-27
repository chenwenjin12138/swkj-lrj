package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.activity.Activity;
import pojo.activity.ActivityTime;

import java.util.List;

/**
 * @author : fl
 * @describe :活动
 * @date : 2020-5-11
 */
public interface IActivityTimeService {

    /**
     * 查询
     * @param activityId 查询条件
     * @return
     */
    List<ActivityTime> getListByParam(int activityId);


    /**
     * 添加
     * @param activityTime
     * @return
     */
    ReturnData<Boolean> add(ActivityTime activityTime) ;

    /**
     * 修改
     * @param activityTime
     * @return
     */
    ReturnData<Boolean> update(ActivityTime activityTime) ;


    /**
     * 删除
     * @param id
     * @return
     */
    ReturnData<Boolean> delete(int id);


}
