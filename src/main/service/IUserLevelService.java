package service;

import dto.ReturnData;
import pojo.UserLevel;

/**
 * @author : fl
 * @describe : 余额管理
 * @date : 2020-4-28
 */
public interface IUserLevelService {

    /**
     * 查找用户余额
     * @param userId
     * @return
     */
    UserLevel findByUserId(String userId);

    /**
     * 修改余额信息
     * @param userLevel
     * @return
     */
    ReturnData<Boolean> updateUserLevel(UserLevel userLevel);

}
