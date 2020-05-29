package controller.test;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import dto.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.order.Order;
import service.IOrderService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author fl
 * @Description:
 * @date 2020/4/30 0030下午 2:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void getOrderPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(15);
        PageInfo pageInfo = orderService.getOrderPageByParam(requestDTO);
        for (Object or:pageInfo.getList()) {
            System.out.println("数据:"+or.toString());
        }
    }

    @Test
    public void getAppOrderListByParam() {
        RequestDTO requestDTO = new RequestDTO();
        Order order = new Order();
        order.setUserId(47865);
        requestDTO.setObject(order);
        List<Order> list = orderService.getAppOrderListByParam(requestDTO);
        assertEquals(list.size(),65);
    }

}