package controller.test;

import dto.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.order.OrderMonthCard;
import service.IOrderMonthCardService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/14 0014下午 3:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMonthCardServiceImplTest {

    @Autowired
    private IOrderMonthCardService monthCardService;
    @Test
    public void getOrderPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(3);
           LocalDateTime start =
                LocalDateTime.parse("2020-01-01 15:55:31", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end =
                LocalDateTime.parse("2020-07-05 15:55:31", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        requestDTO.setStartLocalDateTime(start);
        requestDTO.setEndLocalDateTime(end);
        assertEquals(monthCardService.getOrderPageByParam(requestDTO).getSize(),1);
    }
}