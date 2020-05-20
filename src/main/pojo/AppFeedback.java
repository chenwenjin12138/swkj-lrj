package pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author fl
 * @descrip: 用户反馈
 * @date 2020/5/20 0020上午 9:44
 */
@Data
public class AppFeedback {
    @TableId
    private int appFeedbackId; //ID
    private Integer userId; //用户ID
    private Integer adminId; //客服ID
    private String type; //反馈类别:0:默认无意义 1:用户反馈 2:客服回复
    private String detail = ""; //内容
    private String contact = ""; //联系方式
    private String createTime = ""; //创建时间

    public static final String CONTACT_COLUMN = "contact";
}
