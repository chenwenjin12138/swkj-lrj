package controller.user;

import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : cwj
 * @describe : 用户控制中心
 * @date : 2020-3-18
 */
@RestController
public class UserController {

    /**
     * 用户手机号+密码登录
     * @param userPhone
     * @param passWord
     * @param request
     * @return
     */
    @RequestMapping(value = "/userLogin",method = {RequestMethod.GET,RequestMethod.POST})
    public Map<String,Object> userLogin(String userPhone,String passWord,HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("requestStatus", "success");
        try {
            if(StringUtils.isEmpty(userPhone) || StringUtils.isEmpty(passWord)){
                resultMap.put("errorCode", 1);
                resultMap.put("errorTip", "参数有误,请检查参数");
                return resultMap;
            }
        }catch (Exception e){
            resultMap.put("errorCode", 2);
            resultMap.put("errorTip","系统异常，登录失败");
            return resultMap;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userPhone, passWord);
        SecurityUtils.getSubject().login(token);
        return resultMap;
    }
}
