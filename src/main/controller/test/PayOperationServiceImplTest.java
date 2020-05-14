package controller.test;

import dto.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.PayOperation;
import service.IPayOperationService;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/14 0014下午 2:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayOperationServiceImplTest {
    @Autowired
    private IPayOperationService payOperationService;

    @Test
    public void getPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(2);
        PayOperation payOperation = new PayOperation();
        payOperation.setTradeSource(0);
        requestDTO.setObject(payOperation);
        assertEquals(payOperationService.getPageByParam(requestDTO).getList().size(),1);
    }
}