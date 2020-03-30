package service.impl;

import mapper.ISysManagementMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import service.ISysManagementService;
import service.ISysUserInfoService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : cwj
 * @describe : 系统管理 服务层
 * @date : 2020-3-26 11:41:18
 */
@Component
@Service
@Transactional
public class SysManagementServiceImpl implements ISysManagementService {

    @Resource
    private ISysManagementMapper sysManagementMapper;
    @Resource
    private ISysUserInfoService sysUserInfoService;
    @Override
    public List<SysUser> findAccountList() {
        return sysManagementMapper.getAccountList();
    }

    @Override
    public List<SysRole> findRoleList() {
        return sysManagementMapper.getRoleList();
    }

    @Override
    public List<SysAuthority> findAuthorityList() {
        return sysManagementMapper.getAuthorityList();
    }

    @Override
    public SysUser findAccountByAdminName(String adminName) {
        return sysUserInfoService.getUserInfoByLoginInfo(adminName);
    }

    @Override
    public Integer addSysUser(SysUser sysUser) {
        //添加系统用户
        SysUser sysUserNew = new SysUser();
        sysUserNew.setAdminName(sysUser.getAdminName());
        sysUserNew.setAdminPassword(sysUser.getAdminPassword());
        sysUserNew.setActive(1);
        sysUserNew.setSysAdminRoles(sysUser.getSysAdminRoles());
        sysUserNew.setCreateTime(new Date());
        return sysManagementMapper.insertSysUser(sysUser);
    }
}
