package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.activity.ActivityItem;

import java.util.List;

/**
 * @author : fl
 * @describe :活动详情
 * @date : 2020-5-11
 */
public interface IActivityItemService {

    /**
     * 查询
     * @param activityId 查询条件
     * @return
     */
    List<ActivityItem> getListByParam(int activityId);


    /**
     * 添加
     * @param activityItem
     * @return
     */
    ReturnData<Boolean> add(ActivityItem activityItem) ;

    /**
     * 修改
     * @param activityItem
     * @return
     */
    ReturnData<Boolean> update(ActivityItem activityItem) ;


    /**
     * 删除
     * @param id
     * @return
     */
    ReturnData<Boolean> delete(int id);


}
