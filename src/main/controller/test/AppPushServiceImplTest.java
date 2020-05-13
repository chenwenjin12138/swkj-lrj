package controller.test;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.AppPush;
import service.IAppPushService;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/13 0013上午 10:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppPushServiceImplTest {
    @Autowired
    IAppPushService appPushService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getMessagePageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(0);
        requestDTO.setSize(1);
        PageInfo pageInfo = appPushService.getMessagePageByParam(requestDTO);
        for (Object or:pageInfo.getList()) {
            System.out.println("数据:"+or.toString());
        }
    }

    @Test
    public void addPush() {
        AppPush push = new AppPush();
        push.setAdminId(24);
        push.setTitle("放假了");
        push.setAlert("今天放假");
        push.setAppPushId(1);
        try {
            appPushService.addPush(push);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePush() {
        AppPush push = new AppPush();
        push.setAppPushId(1);
        try {
           assertTrue(appPushService.deletePush(push).getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}