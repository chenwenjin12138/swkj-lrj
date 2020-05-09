package controller.test;

import mapper.IItemCatMapper;
import mapper.IItemMapper;
import mapper.IMonthCardMapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.AppItem;
import pojo.AppItemCat;
import pojo.MonthCard;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;

import static dto.ReturnData.SUCCESS_CODE;


/**
 * @author Lxh
 * @date 2020/5/7 20:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LxhTest {
    @Resource
   private IItemMapper itemMapper;
    @Resource
    private IItemCatMapper itemCatMapper;
    @Resource
    private IMonthCardMapper monthCardMapper;

    @Test
    public void test(){
        Example example = new Example(MonthCard.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("name","家庭升级");
        List<MonthCard> monthCards = monthCardMapper.selectByExample(example);
        for (MonthCard monthCard : monthCards) {
            System.out.println(monthCard.toString());
        }
        /*Example example = new Example(AppItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("appItemId",1).andEqualTo("itemCategoryId",6);
        List<AppItem> appItems = itemMapper.selectByExample(example);
        for (AppItem appItem : appItems) {
            System.out.println(appItem.toString());
        }*/
       /* Example example = new Example(AppItem.class);
        RowBounds rowBounds = new RowBounds(0, 10);
        List<AppItem> appItems = itemMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (AppItem appItem : appItems) {
            AppItemCat appItemCat = itemCatMapper.selectByPrimaryKey(appItem.getAppItemId());
            appItem.setItemCategoryName(appItemCat.getCategoryName());
            System.out.println(appItem.toString());*/
        }
    }

