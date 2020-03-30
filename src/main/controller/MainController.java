package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : cwj
 * @describe : 控制台Controller
 * @date : 2020-3-26
 */
@Controller
public class MainController {
    /**
     * 后台 控制台
     * @return
     */
    @RequestMapping("/main")
    public String toIndex(){
        return "main/main";
    }
}
