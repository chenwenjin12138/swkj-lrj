package service.task;

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
    private ISysCouponService sysCouponService;

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
            UserCoupon userCouponParam = new UserCoupon();
            userCouponParam.setSource(CouponConstant.ROUSE);
            userCouponParam.setActive(Constant.ACTIVE);
            userCouponParam.setUseStatus(CouponConstant.NOT_USED);
            requestDTO.setObject(userCouponParam);
            List<UserCoupon> couponList  = userCouponService.getListByParam(requestDTO);
            if (couponList != null  && couponList.size() > 0) {
                continue;
            }
            Order order = new Order();
            order.setUserId(appUser1.getAppUserId());
            requestDTO.setObject(order);
            List<Order> orderList =  orderService.getAppOrderListByParam(requestDTO);
            if (orderList == null || orderList.size() == 0) {
                /*RequestDTO sysCouponRequestDTO = new RequestDTO();
                SysCoupon sysCouponParam = new SysCoupon();
                sysCouponParam.setType(CouponConstant.GENERAL);
                sysCouponRequestDTO.setObject(sysCouponParam);
                List<SysCoupon> list =sysCouponService.getListByParam(sysCouponRequestDTO);
                if (list == null || list.size()<1) {
                   return;
                }
                requestDTO.setObject(sysCouponParam);*/
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setUserId(appUser1.getAppUserId());
                userCoupon.setActive(Constant.ACTIVE);
                userCoupon.setSource(CouponConstant.ROUSE);
                userCoupon.setDenomination(new BigDecimal(30));
                userCoupon.setCouponType(CouponConstant.GENERAL);
                LocalDateTime now = LocalDateTime.now();
                userCoupon.setCreateTime(now);
                userCoupon.setLimitTime(now.plusDays(7));
                ReturnData returnData = userCouponService.add(userCoupon);
            }else {
                //查找上个月袋鞋衣家证下单商品排比，比重为：1：2：3：4
                LocalDateTime latestTime = LocalDateTime.parse(orderList.get(0).getCreateTime());
                java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(), latestTime);
                //上次下单距离今天时间差
                long day = duration.toDays();
                int totalDenomination = 0;
                if (orderList.size() <= 3 && day >= 7) {
                    totalDenomination = 100;
                } else if (orderList.size() > 3 && day >= 15) {
                    totalDenomination = 500;
                }else{
                    continue;
                }
                for (int i = 0; i<4;i++) {

                }
            }
        }
    }

}
