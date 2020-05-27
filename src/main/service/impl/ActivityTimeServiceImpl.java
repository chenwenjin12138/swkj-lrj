package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.ActivityTimeMapper;
import org.springframework.stereotype.Service;
import pojo.SysCoupon;
import pojo.activity.Activity;
import pojo.activity.ActivityItem;
import pojo.activity.ActivityTime;
import service.IActivityTimeService;

import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.SysCoupon.ID_COLUMN;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/26 0026上午 9:31
 */
@Service
public class ActivityTimeServiceImpl implements IActivityTimeService {
    private ActivityTimeMapper activityTimeMapper;

    public ActivityTimeServiceImpl(ActivityTimeMapper activityTimeMapper) {
        this.activityTimeMapper = activityTimeMapper;
    }

    @Override
    public List<ActivityTime> getListByParam(int activityId) {
        QueryWrapper<ActivityTime> timeQueryWrapper = new QueryWrapper();
        timeQueryWrapper.eq(ActivityItem.ACTIVITY_ID_COLUMN,activityId);
        return activityTimeMapper.selectList(timeQueryWrapper);
    }

    @Override
    public ReturnData<Boolean> add(ActivityTime activityTime) {
       /* try {
            if (activityTimeMapper.insert(activityTime)> 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> update(ActivityTime activityTime) {
        UpdateWrapper<ActivityTime> updateWrapper = new UpdateWrapper<ActivityTime>();
       /* try {
            updateWrapper.eq(ActivityTime.ID_COLUMN,activityTime.getId());
            if (activityTimeMapper.update(activityTime,updateWrapper)> 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> delete(int id) {
        try {
            if (activityTimeMapper.deleteById(id) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }
}
