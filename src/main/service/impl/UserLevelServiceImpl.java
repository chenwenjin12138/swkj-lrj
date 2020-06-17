package service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import dto.ReturnData;
import mapper.UserLevelMapper;
import org.springframework.stereotype.Service;
import pojo.UserLevel;
import pojo.activity.ActivityTime;
import service.IUserLevelService;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

@Service
public class UserLevelServiceImpl implements IUserLevelService {
    private UserLevelMapper userLevelMapper;

    public UserLevelServiceImpl(UserLevelMapper userLevelMapper) {
        this.userLevelMapper = userLevelMapper;
    }

    @Override
    public UserLevel findByUserId(String userId) {
        return userLevelMapper.selectById(userId);
    }

    @Override
    public ReturnData<Boolean> updateUserLevel(UserLevel userLevel) {
        UpdateWrapper<UserLevel> updateWrapper = new UpdateWrapper<UserLevel>();
        try {
            updateWrapper.eq(ActivityTime.ID_COLUMN,userLevel.getUserId());
            if (userLevelMapper.update(userLevel,updateWrapper)> 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }
}
