package controller.test;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.AppFeedback;
import pojo.activity.Activity;
import pojo.activity.ActivityItem;
import pojo.activity.ActivityTime;
import pojo.activity.ActivityVo;
import service.IActivityService;
import service.IAppFeedbackService;
import util.DateUtil;
import util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/13 0013下午 3:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceImplTest {

    @Autowired
    private IActivityService activityService;

    @Test
    public void getListByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(2);
        PageInfo<ActivityVo> page = activityService.getPageByParam(requestDTO);
        for (Object or : page.getList()) {
            System.out.println("数据:" + or.toString());
        }
    }

    @Test
    public void add() {
        ActivityVo activityVo = new ActivityVo();
        Activity activity = new Activity();
        activity.setName("买一送二");
        activity.setContent("新老用户分享订单即可享受等价免职");
        activity.setDeleted(0);
        activity.setShowType(1);
        activity.setBeginDate("2020-05-20");
        activity.setEndDate("2021-05-20");
        activity.setCreateUser("fl");
        activity.setCreateTime(DateUtil.toOracleTime(new Date()));
        activityVo.setActivity(activity);

        ActivityTime activityTime = new ActivityTime();
        activityTime.setPeriod(1);
        activityTime.setBeginTime("00:00:00");
        activityTime.setEndTime("12:00:00");

        ActivityItem item = new ActivityItem();
        item.setActivityNumber(100);
        item.setReduceType(1);
        item.setUserType(1);
        item.setReduceQuota(-1);

        List<ActivityItem> itemList =new ArrayList<ActivityItem>();
        itemList.add(item);
        activityVo.setActivityItemList(itemList);

        List<ActivityTime> timeList =new ArrayList<ActivityTime>();
        timeList.add(activityTime);
        activityVo.setActivityTimeList(timeList);
        activityService.add(activityVo);
    }

    @Test
    public void delete() {
    }
}