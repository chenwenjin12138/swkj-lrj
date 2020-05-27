package controller.test;

import ch.qos.logback.core.sift.AppenderFactory;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.AppFeedback;
import pojo.ValueAddedServices;
import service.IAppFeedbackService;
import service.IValueAddServicesService;
import util.DateUtil;
import util.DateUtils;

import java.math.BigDecimal;
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
public class AppFeedbackServiceImplTest {

    @Autowired
    private IAppFeedbackService appFeedbackService;

    @Test
    public void getListByParam() {
        RequestDTO requestDTO = new RequestDTO();
      /*  AppFeedback appFeedback = new AppFeedback();
        appFeedback.setContact("489");
        requestDTO.setObject(appFeedback);*/
        PageInfo<AppFeedback> page = appFeedbackService.getPageByParam(requestDTO);
        for (Object or : page.getList()) {
            System.out.println("数据:" + or.toString());
        }
    }

    @Test
    public void add() {
        AppFeedback appFeedback = new AppFeedback();
        appFeedback.setAdminId(1);
        appFeedback.setContact("18388202489");
        appFeedback.setDetail("开发测试");
        appFeedback.setType("2");
        appFeedback.setUserId(23);
        appFeedback.setCreateTime(DateUtils.getNowDateTime());
        assertTrue(appFeedbackService.add(appFeedback).getObject());
    }

    @Test
    public void delete() {
        assertTrue(appFeedbackService.delete(3).getObject());
    }
}