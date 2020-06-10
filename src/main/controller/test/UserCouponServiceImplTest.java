package controller.test;

import dto.RequestDTO;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.UserCoupon;
import service.IUserCouponService;
import service.task.CouponTask;
import service.task.TimeLimitSaleTask;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/14 0014上午 9:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCouponServiceImplTest {
    @Autowired
    private IUserCouponService userCouponService;

    @Autowired
    private CouponTask  couponTask;

    @Autowired
    private TimeLimitSaleTask timeLimitSaleTask;

    @Test
    public void getListByParam() {
        RequestDTO requestDTO = new RequestDTO();
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserId(2);
        requestDTO.setObject(userCoupon);
        List list = userCouponService.getListByParam(requestDTO);
        assertEquals(list.size(),0);
    }

    @Test
    public void grantCoupon() {
        couponTask.grantCoupon();
    }


    @Test
    public void updateCoupon() {
        couponTask.updateUserCoupon();
    }

    @Test
    public void grantTimeLimitSale() {
        timeLimitSaleTask.grantTimeLimitSale();
    }
}