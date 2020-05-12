package controller.test;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.OrderComment;
import service.IOrderCommentService;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/12 0012下午 5:30
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderCommentServiceImplTest {
    @Autowired
    private IOrderCommentService orderCommentService;

    @Test
    public void getOrderCommentPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(15);
        PageInfo pageInfo = orderCommentService.getOrderCommentPageByParam(requestDTO);
        for (Object or:pageInfo.getList()) {
            System.out.println("数据:"+or.toString());
        }
    }

    @Test
    public void updateOrder() {
        OrderComment orderComment = new OrderComment();
        orderComment.setOrderCommentId(1);
        orderComment.setIsVisible(1);
        assertTrue(orderCommentService.updateOrder(orderComment).getObject());

    }
}