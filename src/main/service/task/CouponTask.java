package service.task;

import common.Constant;
import dto.RequestDTO;
import dto.ReturnData;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.omg.CORBA.ORB;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pojo.UserCoupon;
import pojo.order.Order;
import pojo.user.AppUser;
import service.IAppUserService;
import service.IOrderService;
import service.IUserCouponService;
import util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author fl
 * @descrip: 红包定时任务
 * @date 2020/5/28 0028下午 2:03
 */
@Component
@EnableScheduling
public class CouponTask {
    private IAppUserService appUserService;
    private IOrderService orderService;
    private IUserCouponService userCouponService;
    /**
     * 系统发放红包
     * 1.没有下过单的新用户发放通用红包 金额30
     * 2.次数不超过3次，间隔1周的用户发送种类红包集合 金额100
     * 3.超过3次间隔2周的用户发放种类红包集 金额 500
     *
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void grantCoupon(){
        RequestDTO requestDTO = new RequestDTO();
        AppUser appUser = new AppUser();
        appUser.setActive(1);
        requestDTO.setObject(appUser);
        List<AppUser> appUserList = appUserService.getAppUserListByParam(requestDTO);
        for (AppUser appUser1:appUserList) {
            Order order = new Order();
            order.setUserId(appUser1.getAppUserId());
            requestDTO.setObject(order);
            List<Order> orderList =  orderService.getAppOrderListByParam(requestDTO);
            if (orderList == null || orderList.size() == 0) {
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setUserId(appUser1.getAppUserId());
                userCoupon.setSysCouponId(0);
                userCoupon.setCreateTime(DateUtil.toOracleTime(new Date()));
                userCoupon.setSource("1");
                userCoupon.setActive(Constant.ACTIVE);
                ReturnData returnData = userCouponService.add(userCoupon);
            } else if (orderList.size() <= 3) {

            } else {

            }
        }
    }

}
