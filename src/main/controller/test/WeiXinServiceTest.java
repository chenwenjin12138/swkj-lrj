package controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.PayOperation;
import service.WeiXinService;

import java.math.BigDecimal;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/19 0019下午 4:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeiXinServiceTest {
    @Autowired
    private WeiXinService weiXinService;

    @Test
    public void refund() {
        PayOperation payOperation = new PayOperation();
        payOperation.setOutTradeNo("1232");
        payOperation.setTotalFee(new BigDecimal(23));
        payOperation.setTransactionId("3453543");
        try {
            weiXinService.doRefund(payOperation);
        } catch (Exception e){
            System.out.println("退款发生异常");
        }
    }

}