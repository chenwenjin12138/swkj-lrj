package controller.test;

import com.sun.org.apache.xpath.internal.operations.Or;
import dto.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.order.OrderCustomHouseVo;
import pojo.order.OrderMonthCard;
import service.IOrderCustomHouseService;
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

    @Autowired
    private IOrderCustomHouseService orderCustomHouseService;
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
       // assertEquals(monthCardService.getOrderPageByParam(requestDTO).getSize(),1);
    }

    @Test
    public void getOrderCustomPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(3);
        LocalDateTime start =
                LocalDateTime.parse("2020-05-27 16:37:21", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end =
                LocalDateTime.parse("2020-05-27 17:30:08", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        requestDTO.setStartLocalDateTime(start);
        requestDTO.setEndLocalDateTime(end);
        OrderCustomHouseVo orderCustomHouseVo = new OrderCustomHouseVo();
        orderCustomHouseVo.setOrderNumber("45739");
        requestDTO.setData(orderCustomHouseVo);
      //  assertEquals(orderCustomHouseService.getPageByParam(requestDTO).getSize(),1);
    }
}