package service.impl;

import com.sun.tools.javadoc.Start;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.IMonthCardMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import pojo.Base;
import pojo.MonthCard;
import service.IMonthCardService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static dto.ReturnData.SUCCESS_CODE;
import static dto.ReturnData.Fail_CODE;
/**
 * @Description: 后台管理系统月卡类
 * @Author Lxh
 * @Date 2020/5/8 20:00
 */
@Service
public class MonthCardServiceImpl implements IMonthCardService {

    @Resource
    private IMonthCardMapper monthCardMapper;

    private ReturnData returnData = new ReturnData();

    /**
     * @param: requestDTO
     * @Description: 月卡分页查询
     * @Author: LxH
     * @Date: 2020/5/8 20:12
     */
    @Override
    public List<MonthCard> getMcPageByParam(RequestDTO requestDTO) {
        Example example = new Example(MonthCard.class);
        int start = requestDTO.getPage()*requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start,requestDTO.getSize());
        return monthCardMapper.selectByExampleAndRowBounds(example,rowBounds);
    }

    /**
     * @param: monthCard
     * @Description: 新增月卡
     * @Author: LxH
     * @Date: 2020/5/8 20:23
     */
    @Override
    public ReturnData<Boolean> addMonthCard(MonthCard monthCard) {
        monthCard.setCreateTime(DateUtils.formatDate(new Date()));
        if (monthCardMapper.insertSelective(monthCard) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("月卡添加成功").setObject(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("月卡添加失败").setObject(false);
    }

    /**
     * @param: monthCard
     * @Description: 月卡更新
     * @Author: LxH
     * @Date: 2020/5/9 9:40
     */
    @Override
    public ReturnData<Boolean> updateMonthCard(MonthCard monthCard) {
        monthCard.setUpdateTime(DateUtils.formatDate(new Date()));
        if (monthCardMapper.insertSelective(monthCard) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("月卡更新成功").setObject(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("月卡更新失败").setObject(false);
    }

    /**
     * @param: monthCard
     * @Description: 设置月卡启用或禁用
     * @Author: LxH
     * @Date: 2020/5/9 10:00
     */
    @Override
    public ReturnData<Boolean> setDisplay(MonthCard monthCard) {
        monthCard.setStatus(monthCard.getStatus());
        if (monthCardMapper.updateByPrimaryKeySelective(monthCard) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("月卡月卡启用或禁用成功").setObject(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("月卡启用或禁用失败").setObject(false);
    }

    /**
     * @param: name
     * @Description: 条件查询月卡
     * @Author: LxH
     * @Date: 2020/5/9 10:25
     */
    @Override
    public MonthCard findCardByName(String name) {
        Example example = new Example(MonthCard.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("name",name);
        List<MonthCard> monthCards = monthCardMapper.selectByExample(example);
        for (MonthCard monthCard : monthCards) {
            return monthCard;
        }
        return null;
    }
}
