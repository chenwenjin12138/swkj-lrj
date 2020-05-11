package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.IBusinessAdminMapper;
import org.springframework.stereotype.Service;
import pojo.user.SysAdmin;
import service.IBusinessAdminService;
import util.RandomUtil;

import java.util.List;

import static dto.ReturnData.SUCCESS_CODE;
import static pojo.user.AppStaff.COLUMN_APP_STAFF_ID;
import static pojo.user.SysAdmin.*;

/**
 * @author fl
 * @Description: 商家管理
 * @date 2020/5/6 0006上午 11:19
 */
@Service
public class BusinessAdminServiceImpl implements IBusinessAdminService {
    private IBusinessAdminMapper iBusinessAdminMapper;
    private ObjectMapper objectMapper;

    public BusinessAdminServiceImpl(IBusinessAdminMapper iBusinessAdminMapper, ObjectMapper objectMapper) {
        this.iBusinessAdminMapper = iBusinessAdminMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public PageInfo<SysAdmin> getBusinessAdminPageByParam(RequestDTO requestDTO) {
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper();
        SysAdmin businessAdmin = objectMapper.convertValue(requestDTO.getObject(), SysAdmin.class);

        if (businessAdmin != null && StringUtils.isNotEmpty(businessAdmin.getBusinessPhone())) {
            queryWrapper.like(COLUMN_BUSINESS_PHONE, businessAdmin.getBusinessPhone());
        }

        if (businessAdmin != null && StringUtils.isNotEmpty(businessAdmin.getAdminName())) {
            queryWrapper.like(COLUMN_BUSINESS_NAME, businessAdmin.getAdminName());
        }

        if (businessAdmin != null && StringUtils.isNotEmpty(businessAdmin.getBusinessContactPerson())) {
            queryWrapper.like(COLUMN_BUSINESS_CONTACT_PERSON, businessAdmin.getBusinessContactPerson());
        }
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<SysAdmin> list = iBusinessAdminMapper.selectList(queryWrapper);
        return new PageInfo<SysAdmin>(list);
    }

    @Override
    public ReturnData<Boolean> updateBusinessAdmin(SysAdmin businessAdmin) {
        UpdateWrapper<SysAdmin> updateWrapper = new UpdateWrapper<SysAdmin>();
        updateWrapper.eq(COLUMN_APP_STAFF_ID, businessAdmin.getSysAdminId());
        return new ReturnData(SUCCESS_CODE,"操作成功",iBusinessAdminMapper.update(businessAdmin, updateWrapper) > 0 ? true : false);
    }

    @Override
    public ReturnData<Boolean> addBusinessAdmin(SysAdmin businessAdmin) {
        businessAdmin.setCreateTime(com.lanrenxiyi.util.DateUtils.getNowDateTime());
        businessAdmin.setInvitationCode("b" + RandomUtil.generateRandomString(10).toLowerCase());
        return new ReturnData(SUCCESS_CODE,"操作成功",iBusinessAdminMapper.insert(businessAdmin) > 0 ? true : false);
    }

}
