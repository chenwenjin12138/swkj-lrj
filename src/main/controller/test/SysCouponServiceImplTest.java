package controller.test;

import dto.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.SysCoupon;
import pojo.ValueAddedServices;
import service.ISysCouponService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/13 0013下午 5:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysCouponServiceImplTest {
    @Autowired
    private ISysCouponService sysCouponService;

    @Test
    public void getListByParam() {
        RequestDTO requestDTO = new RequestDTO();
        List list = sysCouponService.getListByParam(requestDTO);
        for (Object or : list) {
            System.out.println("数据:" + or.toString());
        }
    }

    @Test
    public void add() {
        SysCoupon sysCoupon = new SysCoupon();
        sysCoupon.setAging(3);
        sysCoupon.setDenomination(new BigDecimal("0.5"));
        sysCoupon.setInstructions("5角的红包");
        assertTrue(sysCouponService.add(sysCoupon).getObject());
    }

    @Test
    public void update() {
        SysCoupon sysCoupon = new SysCoupon();
        assertTrue(sysCouponService.update(sysCoupon).getObject());
    }
}