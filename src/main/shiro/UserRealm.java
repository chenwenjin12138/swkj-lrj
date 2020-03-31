package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pojo.SysAuthority;
import pojo.SysUser;
import service.ISysUserInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : cwj
 * @describe : shiro 域对象配置
 * @date : 2020-3-18
 */
@Component
public class UserRealm extends AuthorizingRealm{

    @Resource
    private ISysUserInfoService sysUserInfoService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取用户真实对象
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        //为权限控制提供真实数据
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //获取用户权限，添加到自动验证里
        List<SysAuthority> sysAuthorityList = sysUserInfoService.getSysAuthoritysByAdmin(sysUser);
        info.addStringPermission(String.valueOf(sysAuthorityList));
        return info;
    }

    /**
     * 登录认证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        String passWord = String.valueOf(token.getPassword());

        SysUser sysUser = sysUserInfoService.getUserInfoByLoginInfo(userName);
        if(sysUser.getAdminName().equals(userName)){
            if(sysUser.getAdminPassword().equals(passWord)){
                return new SimpleAuthenticationInfo(sysUser, sysUser.getAdminPassword(), this.getName());
            }else {
                new IncorrectCredentialsException();
            }
        }else {
            new UnknownAccountException();
        }
        return null;
    }
}
