package service;

import dto.RequestDTO;
import dto.ReturnData;
import pojo.MonthCard;

import java.util.List;

/**
 * @Description: 后台管理系统月卡类
 * @Author Lxh
 * @Date 2020/5/8 19:59
 */
public interface IMonthCardService {

    /**
     * @Description: 月卡分页查询
     * @Author: LxH
     * @Date: 2020/5/8 20:12
     */
    List<MonthCard> getMcPageByParam(RequestDTO requestDTO);

    /**
     * @Description: 新增月卡
     * @Author: LxH
     * @Date: 2020/5/8 20:23
     */
    ReturnData<Boolean> addMonthCard(MonthCard monthCard);

    /**
     * @Description: 月卡更新
     * @Author: LxH
     * @Date: 2020/5/9 9:40
     */
    ReturnData<Boolean> updateMonthCard(MonthCard monthCard);

    /**
     * @Description: 设置月卡启用或禁用
     * @Author: LxH
     * @Date: 2020/5/9 10:00
     */
    ReturnData<Boolean> setDisplay(MonthCard monthCard);

    /**
     * @Description: 条件查询月卡
     * @Author: LxH
     * @Date: 2020/5/9 10:25
     */
    MonthCard findCardByName(String name);
}
