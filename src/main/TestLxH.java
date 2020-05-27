import com.alibaba.fastjson.JSON;
import mapper.*;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.*;
import pojo.order.Order;
import pojo.user.AppStaff;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;
import vo.OrderInfo;

import javax.annotation.Resource;

import java.math.BigInteger;
import java.util.List;

/**
 * @Description: LxH测试类
 * @Author Lxh
 * @Date 2020/5/14 17:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = app.class)
public class TestLxH {
    @Resource
    private IItemMapper itemMapper;
    @Resource
    private IOrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private AppStaffOrderMapper appStaffOrderMapper;
    @Resource
    private DistributionrelationMapper distributionrelationMapper;
    @Resource
    private SmsTemplateMapper smsTemplateMapper;
    @Resource
    private MerchantManagementMapper merchantManagementMapper;
    @Resource
    private IMonthCardMapper monthCardMapper;
    @Resource
    private AreaManagementMapper areaManagementMapper;
    @Test
    public void test(){

        List<AreaManagement> areaManagements = areaManagementMapper.selectAll();
        for (AreaManagement areaManagement : areaManagements) {
            System.out.println(areaManagement.toString());
        }
        /*List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.toString());
        }*/
        /*String startTime = null; //DateUtils.getStartTime();
        String endTime = null; //DateUtils.getEndTime();
        Example example = new Example(AppItem.class);
        example.createCriteria().andBetween("createTime",startTime,endTime);
        List<AppItem> appItems = itemMapper.selectByExample(example);
        for (AppItem appItem : appItems) {
            System.out.println(appItem.toString());
        }*/
        /*Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startStr = simpleDateFormat.format(dayStart);
        System.out.println(startStr);

        //一天的结束时间 yyyy:MM:dd 23:59:59
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date dayEnd = calendar.getTime();
        String endStr = simpleDateFormat.format(dayEnd);
        System.out.println(endStr);*/
    }
}
