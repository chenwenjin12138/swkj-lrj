package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.IAppStaffMapper;
import org.springframework.stereotype.Service;
import pojo.user.AppStaff;
import service.IAppStaffService;

import java.lang.reflect.Field;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.user.AppStaff.*;
import static common.Constant.NOT_DELETED;


@Service
public class AppStaffServiceImpl implements IAppStaffService {
    private IAppStaffMapper iAppStaffMapper;
    private ObjectMapper objectMapper = new ObjectMapper();

    public AppStaffServiceImpl(IAppStaffMapper iAppStaffMapper) {
        this.iAppStaffMapper = iAppStaffMapper;
    }

    @Override
    public PageInfo<AppStaff> getAppUserPageByParam(RequestDTO requestDTO) {
        QueryWrapper<AppStaff> queryWrapper = new QueryWrapper();
        AppStaff staff = objectMapper.convertValue(requestDTO.getObject(), AppStaff.class);
        queryWrapper.eq(COLUMN_IS_DELETED, NOT_DELETED);
        queryWrapper.eq(COLUMN_STAFF_USER,staff.getStaffUser());
        if (staff != null && StringUtils.isNotEmpty(staff.getTelephone())) {
            queryWrapper.like(COLUMN_TELEPHONE, staff.getTelephone());
        }
        if (staff != null && StringUtils.isNotEmpty(staff.getTelephone())) {
            queryWrapper.like(COLUMN_REAL_NAME, staff.getRealName());
        }
        if (staff != null && StringUtils.isNotEmpty(staff.getStaffUser())) {
            queryWrapper.like(COLUMN_STAFF_USER, staff.getStaffUser());
        }
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<AppStaff> list = iAppStaffMapper.selectList(queryWrapper);
        return new PageInfo<AppStaff>(list);
    }


    @Override
    public ReturnData updateAppStaff(AppStaff appStaff) {
        UpdateWrapper<AppStaff> updateWrapper = new UpdateWrapper<AppStaff>();
        updateWrapper.eq(COLUMN_APP_STAFF_ID, appStaff.getAppStaffId());
        if (iAppStaffMapper.update(appStaff, updateWrapper) > 0 ) {
            return new ReturnData(SUCCESS_CODE,"操作成功", true);
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData addAppStaff(AppStaff appStaff) {
        if (iAppStaffMapper.insert(appStaff) > 0) {
            return new ReturnData(SUCCESS_CODE,"操作成功", true);
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }
}
