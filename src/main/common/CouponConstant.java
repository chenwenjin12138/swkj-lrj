package common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fl
 * @descrip: 红包常量类
 * @date 2020/6/1 0001下午 2:06
 */
public class CouponConstant {
    /**
     * 通用红包
     */
    public static final int GENERAL = 1;
    /**
     * 衣物红包
     */
    public static final int CLOTHING = 2;
    /**
     * 袋洗红包
     */
    public static final int BAG_TO_WASH = 3;
    /**
     * 通用红包
     */
    public static final int SHOES = 4;
    /**
     * 家政红包
     */
    public static final int HOMEMAKING = 5;
    /**
     * 月卡红包
     */
    public static final int MONTH_CARD = 6;

    /**
     * 后台员工发放
     */
    public static final int BACKGROUND_SEND = 1;

    /**
     * 订单分享红包
     */
    public static final int ORDER_SHARE = 2;

    /**
     * 系统唤醒
     */
    public static final int ROUSE = 3;

    /**
     * 已使用
     */
    public static final int USED = 1;

    /**
     * 未使用
     */
    public static final int NOT_USED = 0;


    @AllArgsConstructor
    @Getter
    public enum CouponType{
        GENERAL(1,"通用红包，不限种类"),
        CLOTHING(2,"衣物红包，下单衣物类商品时可使用"),
        BAG_TO_WASH(3,"袋洗红包，下单袋洗商品时可使用"),
        SHOES(4,"鞋类红包，下单鞋类商品时可使用");

        private int  code;
        private String useInstructions;

        public static String getUseInstructions(int code){
            for (CouponType couponType:CouponType.values()) {
                if (code == couponType.getCode() ) {
                    return couponType.getUseInstructions();
                }
            }
            return null;
        }
    }


}


