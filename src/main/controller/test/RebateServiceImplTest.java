package controller.test;

import dto.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Rebate;
import service.RebateService;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RebateServiceImplTest {

    @Autowired
    private RebateService rebateService;

    @Test
    public void getPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(2);
        requestDTO.setStartLocalDateTime(LocalDateTime.now().minusDays(20));
        requestDTO.setEndLocalDateTime(LocalDateTime.now());
        Rebate rebate = new Rebate();
        rebate.setUserId(27);
        requestDTO.setObject(rebate);
        assertEquals(rebateService.getPageByParam(requestDTO).getList().size(),1);
    }
}