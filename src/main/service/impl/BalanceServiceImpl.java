package service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import dto.ReturnData;
import mapper.IBalanceMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Balance;
import service.IBalanceService;
import util.DateUtils;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;


@Service
public class BalanceServiceImpl implements IBalanceService {
     private IBalanceMapper iBalanceMapper;

    public BalanceServiceImpl(IBalanceMapper iBalanceMapper) {
        this.iBalanceMapper = iBalanceMapper;
    }

    @Override
    public Balance findByUserId(String userId) {
       return iBalanceMapper.selectById(userId);
    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public ReturnData<Boolean> updateBalance(Balance balance) {
        UpdateWrapper<Balance> updateWrapper = new UpdateWrapper<Balance>();
        if (StringUtils.isEmpty(balance.getUserId().toString())) {
            return new ReturnData<Boolean>(Fail_CODE,"操作失败,用户id不能为空",false);
        }
        Balance balanceOld = this.findByUserId(balance.getUserId().toString());
        if (balanceOld == null) {
            balance.setCreateTime(DateUtils.getNowDateTime());
            balance.setLastModifyTime(DateUtils.getNowDateTime());
            try {
                if(iBalanceMapper.insert(balance)>0){
                    return new ReturnData(SUCCESS_CODE,"操作成功",true);
                }
                return new ReturnData(Fail_CODE,"操作失败", false);
            } catch (Exception e) {
                e.printStackTrace();
                return new ReturnData(Fail_CODE,"操作失败服务异常", false);
            }
        }
        updateWrapper.eq(Balance.COLUMN_USER_ID, balance.getUserId());
        if (iBalanceMapper.update(balance, updateWrapper) > 0) {
            return new ReturnData(SUCCESS_CODE,"操作成功",true);
        }
        return new ReturnData(Fail_CODE,"操作失败", false);
    }
}
