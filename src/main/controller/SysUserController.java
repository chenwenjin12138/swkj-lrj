package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.regexp.internal.RE;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pojo.SysUser;
import pojo.user.AppStaff;
import pojo.user.AppUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : cwj
 * @describe : 用户控制中心
 * @date : 2020-3-18
 */
@Controller
@Api(tags = "用户控制中心")
public class SysUserController{

    /**
     * 后台系统登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    /**
     * 后台管理系统登录：用户名+密码登录
     * @return
     */
    @ApiOperation("用户登录")
    @RequestMapping(value = "/userLogin",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ReturnData<Boolean> userLogin(@RequestBody SysUser sysUser){
        String userName = sysUser.getAdminName();
        String passWord = sysUser.getAdminPassword();
        try {
            if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){
              return new ReturnData<>(ReturnData.Fail_CODE,"参数有误,请检查参数",false);
            }
        }catch (Exception e){
            return new ReturnData<>(ReturnData.Fail_CODE,"系统异常，登录失败",false);

        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return new ReturnData<>(ReturnData.Fail_CODE,"未知账户",false);
        } catch (IncorrectCredentialsException ice) {
            return new ReturnData<>(ReturnData.Fail_CODE,"密码错误",false);

        }catch (AuthenticationException ae) {
            return new ReturnData<>(ReturnData.Fail_CODE,"用户名或密码不正确",false);

        }
        if (subject.isAuthenticated()) {
            System.out.println("认证成功了！。。。。。。。");
            return new ReturnData<>(ReturnData.SUCCESS_CODE,"登录成功",true);
        } else {
            token.clear();
            return new ReturnData<>(ReturnData.SUCCESS_CODE,"登录失败,请联系客服人员",true);
        }
    }

}
