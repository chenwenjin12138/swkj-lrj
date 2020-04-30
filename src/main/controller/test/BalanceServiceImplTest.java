package controller.test;

import mapper.IBalanceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.IBalanceService;

import static org.junit.Assert.*;

/**
 * @author fl
 * @Description:
 * @date 2020/4/30 0030下午 5:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BalanceServiceImplTest {
    @Autowired
    private IBalanceService balanceService;

    @Test
    public void findByUserId() {
        System.out.println(balanceService.findByUserId("1"));
    }
}
