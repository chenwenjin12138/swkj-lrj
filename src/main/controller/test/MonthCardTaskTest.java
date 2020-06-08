package controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.MonthCard;
import service.IOrderCommentService;
import service.IOrderMonthCardService;
import service.task.MonthCardTask;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/6/8 0008下午 4:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MonthCardTaskTest {
    @Autowired
    private MonthCardTask monthCardTask;
    @Test
    public void updateUserCoupon() {
      //  monthCardTask.updateOrderMonthCard();
        monthCardTask.updateOrderCustomHouse();
    }
}