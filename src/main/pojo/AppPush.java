package pojo;

import lombok.Data;

/**
 * @author fl
 * @descrip: 消息推送
 * @date 2020/5/13 0013上午 10:22
 */
@Data
public class AppPush {
    private Integer appPushId;
    /**
     * 创建人
     */
    private Integer adminId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String alert ;

    /**
     * 创建时间
     */
    private String createTime;


    public static final String CREATE_TIME_COLUMN = "create_time";
}
