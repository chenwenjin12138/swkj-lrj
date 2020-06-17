package service.impl;

import dto.RequestDTO;
import dto.ReturnData;
import mapper.ICardAndItemCatMapper;
import mapper.IItemCatMapper;
import mapper.IItemMapper;
import mapper.IMonthCardMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import pojo.AppItem;
import pojo.AppItemCat;
import pojo.CardAndItemCat;
import pojo.MonthCard;
import service.IMonthCardService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;
import vo.Page;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Resource
    private ICardAndItemCatMapper cardAndItemCatMapper;

    @Resource
    private IItemCatMapper itemCatMapper;

    @Resource
    private IItemMapper itemMapper;

    private ReturnData returnData = new ReturnData();

    /**
     * @param: requestDTO
     * @Description: 月卡分页查询
     * @Author: LxH
     * @Date: 2020/5/8 20:12
     */
    @Override
    public Page<MonthCard> getMcPageByParam(MonthCard monthCard,RequestDTO requestDTO) {

        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> num = new ArrayList<Integer>();
        Example example = new Example(MonthCard.class);
        example.orderBy("createTime");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",1);
        if (monthCard.getName()!=null) {
            criteria.andLike("name","%"+monthCard.getName()+"%");
        }
        int start = requestDTO.getPage()*requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start,requestDTO.getSize());
        List<MonthCard> monthCards = monthCardMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (MonthCard card : monthCards) {
            Example example1 = new Example(CardAndItemCat.class);
            example1.createCriteria().andEqualTo("cardId", card.getCardId());
            List<CardAndItemCat> cardAndItemCats = cardAndItemCatMapper.selectByExample(example1);
                for (CardAndItemCat cardAndItemCat : cardAndItemCats) {
                    Integer itemNum = cardAndItemCat.getItemNum();
                    AppItem appItem = itemMapper.selectByPrimaryKey(cardAndItemCat.getItemId());
                    name.add(appItem.getItemName());
                    num.add(itemNum);
                }
                card.setAppItemName(name).setItemNum(num);
            }
        return new Page<MonthCard>(monthCards,monthCards.size());
    }

    /**
     * @param: monthCard
     * @Description: 新增月卡
     * @Author: LxH
     * @Date: 2020/5/8 20:23
     */
    @Override
    public ReturnData<Boolean> addMonthCard(MonthCard monthCard,Integer[] itemIds,Integer[] itemNum) {
        monthCard.setCreateTime(DateUtils.formatDate(new Date()));
        if (itemIds.length!=itemNum.length) {
            return returnData.setCode(Fail_CODE).setMessage("月卡添加失败,参数不匹配").setData(false);
        }
        if (monthCardMapper.insertSelective(monthCard) > 0) {
            for (int i = 0; i < itemIds.length; i++) {
                CardAndItemCat cardAndItemCat = new CardAndItemCat();
                cardAndItemCat.setCardId(monthCard.getCardId()).setItemId(itemIds[i]).setItemNum(itemNum[i]);
                cardAndItemCatMapper.insertSelective(cardAndItemCat);
            }
            return returnData.setCode(SUCCESS_CODE).setMessage("月卡添加成功").setData(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("月卡添加失败").setData(false);
    }

    /**
     * @param: monthCard
     * @Description: 月卡更新
     * @Author: LxH
     * @Date: 2020/5/9 9:40
     */
    @Override
    public ReturnData<Boolean> updateMonthCard(MonthCard monthCard,List<CardAndItemCat> cardAndItemCatList) {
        for (CardAndItemCat cardAndItemCat : cardAndItemCatList) {
            CardAndItemCat cardAndItemCatNew = cardAndItemCatMapper.selectByPrimaryKey(cardAndItemCat.getId());
            cardAndItemCatNew.setItemId(cardAndItemCat.getItemId()).setItemNum(cardAndItemCat.getItemNum());
            int i = cardAndItemCatMapper.updateByPrimaryKeySelective(cardAndItemCatNew);
            System.out.println("修改"+i);
        }
        monthCard.setUpdateTime(DateUtils.formatDate(new Date()));
        if (monthCardMapper.updateByPrimaryKeySelective(monthCard) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("月卡更新成功").setData(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("月卡更新失败").setData(false);
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
            return returnData.setCode(SUCCESS_CODE).setMessage("月卡月卡启用或禁用成功").setData(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("月卡启用或禁用失败").setData(false);
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
            if (monthCard!=null) {
                return monthCard;
            }
        }
        return null;
    }
}
