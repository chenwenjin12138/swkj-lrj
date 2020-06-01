package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import mapper.ISysAuthorityMapper;
import mapper.ISysRoleMapper;
import mapper.ISysUserInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import pojo.UserInfo;
import pojo.user.AppStaff;
import service.ISysUserInfoService;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

import static pojo.SysRole.SYS_AUTHORITY_ID_COLUMN;
import static pojo.SysRole.SYS_ROLE_ID_COLUMN;

/**
 * @author : cwj
 * @describe : 用户服务层实现
 * @date : 2020-3-18
 */
@Component
@Service
@Transactional
public class SysUserInfoServiceImpl implements ISysUserInfoService {

    private ISysUserInfoMapper sysUserInfoMapper;
    private ISysRoleMapper roleMapper;
    private ISysAuthorityMapper sysAuthorityMapper;

    public SysUserInfoServiceImpl(ISysUserInfoMapper sysUserInfoMapper, ISysRoleMapper roleMapper, ISysAuthorityMapper sysAuthorityMapper) {
        this.sysUserInfoMapper = sysUserInfoMapper;
        this.roleMapper = roleMapper;
        this.sysAuthorityMapper = sysAuthorityMapper;
    }

    @Override
    public SysUser getUserInfoByLoginInfo(String userName) {
        SysUser sysUser = this.sysUserInfoMapper.getUserInfoByLoginInfo(userName);
        return sysUser;
    }

    @Override
    public List<SysAuthority> getSysAuthoritysByAdmin(SysUser sysUser) {
        List<SysAuthority> sysAuthorityList = new ArrayList<SysAuthority>();
        //获取用户具有的角色
        String roles = sysUser.getSysAdminRoles();
        if (StringUtils.isEmpty(roles)) {
            return sysAuthorityList;
        }
        String[] roleArry = roles.split(",");
        //获取角色具有的权限
        for (String role : roleArry) {
            SysRole sysRole = this.getSysRoleByAdminId(Integer.parseInt(role));
            String authoritys = sysRole.getSysRoleAuthoritys();
            if (StringUtils.isEmpty(authoritys)) {
                return sysAuthorityList;
            }
            String[] authoritysArry = authoritys.split(",");
            for (String authority : authoritysArry) {
                SysAuthority sysAuthority = this.getAuthorityByRoleId(Integer.parseInt(authority));
                sysAuthorityList.add(sysAuthority);
            }
        }
        return sysAuthorityList;
    }


    @Override
    public SysRole getSysRoleByAdminId(int id) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq(SYS_ROLE_ID_COLUMN, id);
        return roleMapper.selectOne(queryWrapper);
    }

    @Override
    public SysAuthority getAuthorityByRoleId(Integer id) {
        QueryWrapper<SysAuthority> queryWrapper = new QueryWrapper();
        queryWrapper.eq(SYS_AUTHORITY_ID_COLUMN, id);
        return sysAuthorityMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SysRole> getSysRole() {
        return roleMapper.selectList(null);
    }

    @Override
    public List<SysAuthority> getAllSysAuthority() {
        return sysAuthorityMapper.selectList(null);
    }
}
