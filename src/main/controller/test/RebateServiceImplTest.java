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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RebateServiceImplTest {

    @Autowired
    private RebateService rebateService;

    @Test
    public void getPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(2);
        requestDTO.setSize(2);
        requestDTO.setStartLocalDateTime(LocalDateTime.now().minusDays(20));
        requestDTO.setEndLocalDateTime(LocalDateTime.now());
        Rebate rebate = new Rebate();
        rebate.setUserId(27);
        requestDTO.setData(rebate);
        List<Rebate> list = rebateService.getPageByParam(requestDTO).getList();
        System.out.println(rebateService.getPageByParam(requestDTO).getPages());
        for (Rebate rebate1:list) {
            System.out.println("数据："+rebate1.toString());
        }

        assertEquals(rebateService.getPageByParam(requestDTO).getList().size(),2);
    }
}