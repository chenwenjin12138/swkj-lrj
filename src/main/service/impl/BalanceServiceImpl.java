package service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import dto.ReturnData;
import mapper.IBalanceMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Balance;
import service.IBalanceService;
import util.DateUtils;

import java.math.BigDecimal;

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

    @Transactional(rollbackFor = Exception.class,isolation = Isolation.SERIALIZABLE)
    @Override
    public ReturnData<Boolean> updateBalance(Balance balance) {
        UpdateWrapper<Balance> updateWrapper = new UpdateWrapper<Balance>();
        if (balance == null || StringUtils.isEmpty(balance.getUserId().toString())) {
            return new ReturnData<Boolean>(Fail_CODE,"操作失败,用户id不能为空",false);
        }
        Balance balanceOld = this.findByUserId(balance.getUserId().toString());
        //用戶第一次充值
        if (balanceOld == null) {
            balance.setCreateTime(DateUtils.getNowDateTime());
            balance.setLastModifyTime(DateUtils.getNowDateTime());
            balance.setBalance(balance.getBalance()== null ? new BigDecimal(0):balance.getBalance());
            balance.setTopUpAmount(balance.getBalance());
            balance.setExpendAmount(new BigDecimal(0));
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

        //用户余额更新
        if (balance == null || StringUtils.isEmpty(balance.getBalance().toString())) {
            return new ReturnData<Boolean>(Fail_CODE,"操作失败,变动金额不能为空",false);
        }
        if(balance.getBalance().compareTo(BigDecimal.ZERO)>0) {
            //充值
            balanceOld.setBalance(balanceOld.getBalance().add(balance.getBalance()));
        }else {
            //消费
            if (balance.getBalance().compareTo(balanceOld.getBalance())>0) {
                return new ReturnData<Boolean>(Fail_CODE,"消费金额大于余额",false);
            }
            balanceOld.setBalance(balanceOld.getBalance().add(balance.getBalance()));
            balanceOld.setExpendAmount(balanceOld.getExpendAmount().add(balance.getBalance().abs()));
        }
        balanceOld.setCreateTime(DateUtils.getNowDateTime());
        balanceOld.setLastModifyTime(DateUtils.getNowDateTime());
        updateWrapper.eq(Balance.COLUMN_USER_ID, balanceOld.getUserId());
        try {
            if (iBalanceMapper.update(balanceOld, updateWrapper) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败", false);
    }
}
