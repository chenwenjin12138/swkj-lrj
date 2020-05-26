package controller.item;

import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.CardAndItemCat;
import pojo.MonthCard;
import service.IMonthCardService;
import vo.Page;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 后台管理系统月卡类
 * @Author Lxh
 * @Date 2020/5/8 19:56
 */
@RestController
@RequestMapping("monthCard")
@AllArgsConstructor
@Api(tags = "月卡管理")
public class MonthCardController {

    @Resource
    private IMonthCardService monthCardService;

    /**
     * @Description: 月卡分页查询
     * @Author: LxH
     * @Date: 2020/5/8 20:17
     */
    @ApiOperation(value = "月卡分页查询")
    @RequestMapping("/getMcPageByParam")
    public Page<MonthCard> getMcPageByParam(MonthCard monthCard ,RequestDTO requestDTO) {
        return monthCardService.getMcPageByParam(monthCard,requestDTO);
    }

    /**
     * @Description: 新增月卡
     * @Author: LxH
     * @Date: 2020/5/8 20:21
     */
    @ApiOperation(value = "新增月卡")
    @RequestMapping("/addMonthCard")
    public ReturnData<Boolean> addMonthCard(MonthCard monthCard,Integer[] appItemCategoryIds,Integer[] categoryNum){
        return monthCardService.addMonthCard(monthCard,appItemCategoryIds,categoryNum);
    }

    /**
     * @Description: 月卡更新
     * @Author: LxH
     * @Date: 2020/5/9 9:38
     */
    @ApiOperation(value = "月卡更新")
    @RequestMapping("/updateMonthCard")
    public ReturnData<Boolean> updateMonthCard(MonthCard monthCard, @RequestParam("cardAndItemCatList")List<CardAndItemCat> cardAndItemCatList){
        return monthCardService.updateMonthCard(monthCard,cardAndItemCatList);
    }

    /**
     * @Description: 设置月卡启用或禁用
     * @Author: LxH
     * @Date: 2020/5/9 9:54
     */
    @ApiOperation(value = "设置月卡启用或禁用")
    @RequestMapping("/setDisplay")
    public ReturnData<Boolean> setDisplay(MonthCard monthCard){
        return monthCardService.setDisplay(monthCard);
    }

    /**
     * @Description: 条件查询月卡
     * @Author: LxH
     * @Date: 2020/5/9 10:24
     *//*
    @ApiOperation(value = "条件查询月卡")
    @RequestMapping("/findCardByName")
    public MonthCard findCardByName(String name){
        return monthCardService.findCardByName(name);
    }*/
}
