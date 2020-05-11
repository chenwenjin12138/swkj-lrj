package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.Balance;
import pojo.user.AppUser;
import pojo.user.SysAdmin;

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

    /**
     * 修改余额信息
     * @param balance
     * @return
     */
    ReturnData<Boolean> updateBalance(Balance balance);

}
