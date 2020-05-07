package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import lombok.AllArgsConstructor;
import mapper.IAppUserMapper;
import mapper.IBalanceMapper;
import org.springframework.stereotype.Service;
import pojo.Balance;
import pojo.user.AppUser;
import service.IAppUserService;
import service.IBalanceService;

import java.util.List;

import static pojo.user.AppUser.COLUMN_APP_USER_ID;
import static pojo.user.AppUser.COLUMN_USER_PHONE;


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
}
