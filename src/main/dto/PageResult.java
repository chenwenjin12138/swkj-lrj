package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/29 17:28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 返回状态码
     */
    private Integer code;

    public static final int SUCCESS_CODE = 200;
    public static final int Fail_CODE = 500;
    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回实体
     */
    private int total;

    private List<T> data;
}
