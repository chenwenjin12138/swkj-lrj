package pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author fl
 * @descrip: 用户评论
 * @date 2020/5/12 0012下午 5:13
 */
@Data
public class OrderComment {
    private String commentContent; //评论内容
    private String createTime; //记录时间
    @TableField(exist = false)
    private BigDecimal star; //综合星级评分
    @TableField(exist = false)
    private String commentImage; //评论图片存储区 多张用,隔开
    private String userPhone; //用户手机号
    @TableField(exist = false)
    private List<String> commentImages; //评论图片存储区 多张用,隔开

    @Id
    private Integer orderCommentId; // 订单评论ID
    private Integer userId; // 用户ID
    private Long orderId; // 订单ID

    /**
     * 1 显示 0不显示
     */
    private Integer isVisible;//显示

    public static final String CREATE_TIME_COLUMN = "create_time";
    public static final String ID_COLUMN = "order_comment_id";
}
