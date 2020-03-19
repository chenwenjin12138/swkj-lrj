package shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import pojo.UserInfo;
import service.IUserInfoService;

import javax.annotation.Resource;

/**
 * @author : cwj
 * @describe : shiro 域对象配置
 * @date : 2020-3-18
 */
@Component
public class UserRealm extends AuthorizingRealm{

    @Resource
    private IUserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

       /* //获取用户真实对象
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        //为权限控制提供真实数据
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //获取用户权限，添加到自动验证里
        List<UserPermission> adminPermissionList = userInfoService.getAdminPermissionList(userInfo.getadminId());
        info.addStringPermission(String.valueOf(adminPermissionList));*/

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userPhone = token.getUsername();

        UserInfo userInfo = userInfoService.getUserInfoByLoginInfo(userPhone);

        return new SimpleAuthenticationInfo(userInfo, userInfo.getUserPassword(), this.getName());
    }
}
