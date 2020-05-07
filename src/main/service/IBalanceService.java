package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import pojo.Balance;
import pojo.user.AppUser;

/**
 * @author : fl
 * @describe : 余额管理
 * @date : 2020-4-28
 */
public interface IBalanceService {

    /**
     * 查找用户余额
     * @param userId
     * @return
     */
    Balance findByUserId(String userId);

}
