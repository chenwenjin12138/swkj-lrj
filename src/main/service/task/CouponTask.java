package service.task;

import com.google.gson.internal.$Gson$Preconditions;
import common.Constant;
import common.CouponConstant;
import dto.RequestDTO;
import dto.ReturnData;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pojo.SysCoupon;
import pojo.UserCoupon;
import pojo.order.Order;
import pojo.user.AppUser;
import service.IAppUserService;
import service.IOrderService;
import service.ISysCouponService;
import service.IUserCouponService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private ISysCouponService sysCouponService;
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public CouponTask(IAppUserService appUserService, IOrderService orderService, IUserCouponService userCouponService, ISysCouponService sysCouponService) {
        this.appUserService = appUserService;
        this.orderService = orderService;
        this.userCouponService = userCouponService;
        this.sysCouponService = sysCouponService;
    }

    /**
     * 系统发放唤醒红包 ,有效期都为7天
     * 1.没有下过单的新用户发放通用红包 金额30
     * 2.次数不超过3次，间隔1周的用户发送种类红包集合 金额100
     * 3.超过3次间隔2周的用户发放种类红包集 金额 500
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void grantCoupon() {
        RequestDTO requestDTO = new RequestDTO();
        AppUser appUser = new AppUser();
        appUser.setActive(1);
        requestDTO.setObject(appUser);
        List<AppUser> appUserList = appUserService.getAppUserListByParam(requestDTO);
        for (AppUser appUser1 : appUserList) {
            appUser1.setAppUserId(47835);
            UserCoupon userCouponParam = new UserCoupon();
            userCouponParam.setSource(CouponConstant.ROUSE);
            userCouponParam.setActive(Constant.ACTIVE);
            userCouponParam.setUseStatus(CouponConstant.NOT_USED);
            userCouponParam.setUserId(appUser1.getAppUserId());
            requestDTO.setObject(userCouponParam);
            List<UserCoupon> couponList = userCouponService.getListByParam(requestDTO);
            if (couponList != null && couponList.size() > 0) {
                continue;
            }
            Order order = new Order();
            order.setUserId(appUser1.getAppUserId());
            requestDTO.setObject(order);
            List<Order> orderList = orderService.getAppOrderListByParam(requestDTO);
            if (orderList == null || orderList.size() == 0) {
               /* UserCoupon userCoupon = new UserCoupon();
                userCoupon.setUserId(appUser1.getAppUserId());
                userCoupon.setActive(Constant.ACTIVE);
                userCoupon.setSource(CouponConstant.ROUSE);
                userCoupon.setDenomination(new BigDecimal(30));
                userCoupon.setCouponType(CouponConstant.GENERAL);
                LocalDateTime now = LocalDateTime.now();
                userCoupon.setCreateTime(now);
                userCoupon.setLimitTime(now.plusDays(7));
                userCoupon.setUseStatus(CouponConstant.NOT_USED);
                ReturnData returnData = userCouponService.add(userCoupon);*/
            } else {
                //袋鞋衣家证下单商品排比，比重为：1：2：3：4 随机发送
                List<Double> list = new ArrayList<>();
                list.add(0.1);
                list.add(0.2);
                list.add(0.3);
                list.add(0.4);
                LocalDateTime latestTime = LocalDateTime.parse(orderList.get(0).getCreateTime(),dateTimeFormatter);
                java.time.Duration duration = java.time.Duration.between(latestTime,LocalDateTime.now());
                //上次下单距离今天时间差
                long day = duration.toDays();
                int totalDenomination = 0;
                if (orderList.size() <= 3 && day >= 7) {
                    totalDenomination = 100;
                } else if (orderList.size() > 3 && day >= 15) {
                    totalDenomination = 500;
                } else {
                    continue;
                }
                int total = 200;
                for (int i = 1; i < 5; i++) {
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setUserId(appUser1.getAppUserId());
                    userCoupon.setActive(Constant.ACTIVE);
                    userCoupon.setSource(CouponConstant.ROUSE);
                    userCoupon.setDenomination(new BigDecimal(30));
                    userCoupon.setCouponType(i);
                    userCoupon.setUseInstructions(CouponConstant.CouponType.getUseInstructions(i));
                    Random random = new Random();
                    int n = random.nextInt(list.size());
                    System.out.println(total * list.get(n));
                    userCoupon.setDenomination(new BigDecimal(total * list.get(n)));
                    list.remove(n);
                    LocalDateTime now = LocalDateTime.now();
                    userCoupon.setCreateTime(now);
                    userCoupon.setLimitTime(now.plusDays(7));
                    userCoupon.setUseStatus(CouponConstant.NOT_USED);
                    ReturnData returnData = userCouponService.add(userCoupon);
                }
            }
        }
    }


    /**
     * 每天凌晨1点更新过期红包
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateUserCoupon() {
        RequestDTO requestDTO = new RequestDTO();
        UserCoupon userCouponParam = new UserCoupon();
        userCouponParam.setActive(Constant.ACTIVE);
        requestDTO.setObject(userCouponParam);
        List<UserCoupon> couponList = userCouponService.getListByParam(requestDTO);
        couponList.forEach(coupon->{
            if (coupon.getLimitTime().isBefore(LocalDateTime.now())) {
                coupon.setActive(Constant.FORBIDDEN);
                userCouponService.update(coupon);
            }
        });
    }
}

