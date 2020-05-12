package controller.test;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Order;
import pojo.user.AppStaff;
import service.IAppStaffService;
import service.IOrderService;

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
    public void getAppUserPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(15);
        PageInfo pageInfo = orderService.getOrderPageByParam(requestDTO);
        for (Object or:pageInfo.getList()) {
            System.out.println("数据:"+or.toString());
        }
    }

}