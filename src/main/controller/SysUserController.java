package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.deploy.net.HttpResponse;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     * @param userName
     * @param passWord
     * @param request
     * @return
     */
    @RequestMapping(value = "/userLogin",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> userLogin(String userName, String passWord, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){
                resultMap.put("errorCode", 1);
                resultMap.put("errorTip", "参数有误,请检查参数");
                return resultMap;
            }
        }catch (Exception e){
            resultMap.put("errorCode", 2);
            resultMap.put("errorTip","系统异常，登录失败");
            return resultMap;
        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            resultMap.put("errorCode", 3);
            resultMap.put("errorTip","未知账户");
            return resultMap;
        } catch (IncorrectCredentialsException ice) {
            resultMap.put("errorCode", 4);
            resultMap.put("errorTip","密码错误");
            return resultMap;
        } /*catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } */catch (AuthenticationException ae) {
            resultMap.put("errorCode", 5);
            resultMap.put("errorTip","用户名或密码不正确！");
            return resultMap;
        }
        if (subject.isAuthenticated()) {
            System.out.println("认证成功了！。。。。。。。");
            boolean b2 = subject.isPermitted( "系统后台管理" );   // 这个用户是否有某权限
            System.out.println( b2 );
            resultMap.put("errorCode", 0);
            resultMap.put("errorTip","main");
        } else {
            token.clear();
            resultMap.put("errorCode", 6);
            resultMap.put("errorTip","登录失败,请联系客服人员！");
        }
        return resultMap;
    }

}
