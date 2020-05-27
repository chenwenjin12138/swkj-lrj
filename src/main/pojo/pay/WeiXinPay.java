package pojo.pay;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author fl
 * @descrip: 微信退款请求参数
 * @date 2020/5/19 0019下午 2:19
 */
@Data
public class WeiXinPay {
    /**
     *公众账号ID
     */
    private String appid;

    /**
     *商户号
     */
    private String mch_id;
    /**
     *随机字符串
     */
    private String nonce_str;
    /**
     *签名
     */
    private String sign;
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 退款单号，系统自定义
     */
    private String out_refund_no;

    /**
     * 订单金额
     */
    private BigDecimal total_fee;

    /**
     * 退款金额
     */
    private BigDecimal refund_fee;
}
