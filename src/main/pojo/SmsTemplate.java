package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/20 16:24
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sms_template")
public class SmsTemplate extends Base{
    private static final long serialVersionUID = -6273924435219972922L;
    @Id
    private Integer smsTemplateId; //模板ID
    private long orderId; //订单编号
    private Integer isWearOut; //是否破损:1.有 0.无
    private Integer isDye; //是否染色:1.有 0.无
    private Integer isBallingUp; //是否起球:1.有 0.无
    private Integer isYellowWhite; //是否发黄发白:1.有 0.无
    private Integer isCrossColor; //是否串色:1.有 0.无
    private String other = ""; //其他
    private String orderNumber;
}
