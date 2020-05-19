package pojo;

import java.math.BigDecimal;

/**
 * @author fl
 * @descrip: 退款申请实体类
 * @date 2020/5/15 0015下午 3:22
 */
public class Refund {
    private Integer id;
    private String userId;
    private String userPhone;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 退款金额
     */
    private BigDecimal amount;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 审核状态 0 未审核 1 通过 -1失败
     */
    private Integer checkStatus;

    /**
     * 删除状态 0 未删除 1 已删除
     */
    private Integer active;

    private String createTime;
    private String updateTime;

    /**
     * 退款状态 0未开始处理  1退款进行中 2退款成功 3 退款失败
     */
    private Integer refundStatus;

}
