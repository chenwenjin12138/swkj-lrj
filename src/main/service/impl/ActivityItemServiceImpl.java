package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.ActivityItemMapper;
import org.springframework.stereotype.Service;
import pojo.activity.Activity;
import pojo.activity.ActivityItem;
import pojo.activity.ActivityTime;
import service.IActivityItemService;

import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/26 0026上午 9:33
 */
@Service
public class ActivityItemServiceImpl implements IActivityItemService {
    private ActivityItemMapper activityItemMapper;

    public ActivityItemServiceImpl(ActivityItemMapper activityItemMapper) {
        this.activityItemMapper = activityItemMapper;
    }

    @Override
    public List<ActivityItem> getListByParam(int activityId) {
        QueryWrapper<ActivityItem> itemQueryWrapper = new QueryWrapper();
        itemQueryWrapper.eq(ActivityItem.ACTIVITY_ID_COLUMN,activityId);
        return activityItemMapper.selectList(itemQueryWrapper);
    }

    @Override
    public ReturnData<Boolean> add(ActivityItem activityItem) {
        try {
            if (activityItemMapper.insert(activityItem)> 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> update(ActivityItem activityItem) {
        UpdateWrapper<ActivityItem> updateWrapper = new UpdateWrapper<ActivityItem>();
        try {
            updateWrapper.eq(ActivityTime.ID_COLUMN,activityItem.getId());
            if (activityItemMapper.update(activityItem,updateWrapper)> 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> delete(int id) {
        try {
            if (activityItemMapper.deleteById(id) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }
}
