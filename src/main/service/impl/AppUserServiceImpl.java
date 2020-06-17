package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.Constant;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.IAppUserMapper;
import org.springframework.stereotype.Service;
import pojo.user.AppUser;
import service.IAppUserService;

import java.util.List;

import static pojo.user.AppUser.*;


@Service
public class AppUserServiceImpl implements IAppUserService {
    private IAppUserMapper iAppUserMapper;
    private ObjectMapper objectMapper = new ObjectMapper();
    public AppUserServiceImpl(IAppUserMapper iAppUserMapper) {
        this.iAppUserMapper = iAppUserMapper;
    }

    @Override
    public ReturnData<PageInfo<AppUser>> getAppUserPageByParam(RequestDTO requestDTO) {
        QueryWrapper<AppUser> queryWrapper = new QueryWrapper();
        AppUser appUser = null;
        try {
            appUser = objectMapper.convertValue(requestDTO.getData(), AppUser.class);
            if (appUser != null && StringUtils.isNotEmpty(appUser.getUserPhone())) {
                queryWrapper.like(COLUMN_USER_PHONE, appUser.getUserPhone());
            }
            queryWrapper.orderByDesc(COLUMN_CREATE_TIME);
            PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
            List<AppUser> list = iAppUserMapper.selectList(queryWrapper);
            return new ReturnData<>(ReturnData.SUCCESS_CODE,"操作成功", new PageInfo<AppUser>(list) );
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ReturnData<>(ReturnData.Fail_CODE,"参数错误,操作失败", null);
        }

    }

    @Override
    public List<AppUser> getAppUserListByParam(RequestDTO requestDTO) {
        QueryWrapper<AppUser> queryWrapper = new QueryWrapper();
        AppUser appUser = null;
        try {
            appUser = objectMapper.convertValue(requestDTO.getData(), AppUser.class);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (appUser != null && StringUtils.isNotEmpty(appUser.getActive().toString())) {
            queryWrapper.eq(Column_ACTIVE, Constant.ACTIVE);
        }
        queryWrapper.orderByDesc(COLUMN_CREATE_TIME);
        return iAppUserMapper.selectList(queryWrapper);
    }

    @Override
    public ReturnData<Boolean> updateAppUser(AppUser appUser) {
        UpdateWrapper<AppUser> updateWrapper = new UpdateWrapper<AppUser>();
        updateWrapper.eq(COLUMN_APP_USER_ID, appUser.getAppUserId());
        if (iAppUserMapper.update(appUser, updateWrapper) > 0 ) {
            return new ReturnData<Boolean>(ReturnData.SUCCESS_CODE,"操作成功", true );
        }
        return new ReturnData<Boolean>(ReturnData.Fail_CODE,"操作失败", false);

    }

}
