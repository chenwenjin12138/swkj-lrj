package controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Lxh
 * @date 2020/3/24 15:04
 */
@Controller
public class TestController {
    @RequestMapping("/index")
        public String testIndex(){
        return "item/text";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response) {
        int name = Integer.parseInt(request.getParameter("name"));
        String contentType = request.getContentType();
        String localAddr = request.getLocalAddr();
        System.out.println(name+":"+localAddr);
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }
    @RequestMapping("/page/{moduleName}")
    public String item_add(@PathVariable String moduleName) {

        return moduleName;
    }
}
