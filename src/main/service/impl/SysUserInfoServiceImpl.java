package service.impl;
import mapper.ISysUserInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import pojo.UserInfo;
import service.ISysUserInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : cwj
 * @describe : 用户服务层实现
 * @date : 2020-3-18
 */
@Component
@Service
@Transactional
public class SysUserInfoServiceImpl implements ISysUserInfoService {

    @Resource
    private ISysUserInfoMapper sysUserInfoMapper;

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
        String[] roleArry = StringUtils.split(",",roles);
        //获取角色具有的权限
        for(String role : roleArry){
            SysRole sysRole = this.sysUserInfoMapper.getRoleById(Integer.parseInt(role));
            String authoritys = sysRole.getSysRoleAuthoritys();
            String[] authoritysArry = StringUtils.split(",", authoritys);
            for(String authority : authoritysArry){
                SysAuthority sysAuthority = this.sysUserInfoMapper.getAuthoritById(Integer.parseInt(authority));
                sysAuthorityList.add(sysAuthority);
            }
        }
        return sysAuthorityList;
    }

    @Override
    public SysRole getSysRoleByAdminId(Integer adminId) {
        return null;
    }

    @Override
    public SysAuthority getAuthorityByRoleId(Integer roleId) {
        return null;
    }


}
