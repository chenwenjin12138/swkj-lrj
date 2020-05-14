package pojo;

import lombok.Data;

/**
 * @author fl
 * @Description: 订单流水
 * @date 2020/5/7 0007下午 3:17
 */
@Data
public class PayOperation {
    private Integer id;
    /**
     * 充值平台
     * 1.余额
     * 2.微信
     * 3.支付宝
     */
    private Integer tradeSource;
    /**
     * 1 充值 2提现
     */
    private Integer tradeType;
    /**
     *付款银行
     */
    private String bankType;
    /**
     * 金额
     */
    private String totalFee;
    /**
     *第三方支付订单号
     */
    private String transactionId;
    /**
     * 订单编号
     */
    private String outTradeNo;
    private String createTime;
    private Integer userId;

    public static final String TRADE_SOURCE_COLUMN = "trade_source";
    public static final String TRADE_TYPE_COLUMN = "trade_type";
}
