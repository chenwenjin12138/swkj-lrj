package controller.item;

import com.sun.org.apache.regexp.internal.RE;
import dto.RequestDTO;
import dto.ReturnData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.MonthCard;
import service.IMonthCardService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 后台管理系统月卡类
 * @Author Lxh
 * @Date 2020/5/8 19:56
 */
@RestController
public class MonthCardController {

    @Resource
    private IMonthCardService monthCardService;

    /**
     * @Description: 月卡分页查询
     * @Author: LxH
     * @Date: 2020/5/8 20:17
     */
    @RequestMapping("/getMcPageByParam")
    public List<MonthCard> getMcPageByParam(RequestDTO requestDTO) {
        return monthCardService.getMcPageByParam(requestDTO);
    }

    /**
     * @Description: 新增月卡
     * @Author: LxH
     * @Date: 2020/5/8 20:21
     */
    @RequestMapping("/addMonthCard")
    public ReturnData<Boolean> addMonthCard(MonthCard monthCard){
        return monthCardService.addMonthCard(monthCard);
    }

    /**
     * @Description: 月卡更新
     * @Author: LxH
     * @Date: 2020/5/9 9:38
     */
    @RequestMapping("/updateMonthCard")
    public ReturnData<Boolean> updateMonthCard(MonthCard monthCard){
        return monthCardService.updateMonthCard(monthCard);
    }

    /**
     * @Description: 设置月卡启用或禁用
     * @Author: LxH
     * @Date: 2020/5/9 9:54
     */
    @RequestMapping("/setDisplay")
    public ReturnData<Boolean> setDisplay(MonthCard monthCard){
        return monthCardService.setDisplay(monthCard);
    }

    /**
     * @Description: 条件查询月卡
     * @Author: LxH
     * @Date: 2020/5/9 10:24
     */
    @RequestMapping("/findCardByName")
    public MonthCard findCardByName(String name){
        return monthCardService.findCardByName(name);
    }
}