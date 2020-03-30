package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Lxh
 * @date 2020/3/23 9:25
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int flag;// 0:代表执行成功,1:处理失败,2:代表程序异常
    private String message;// 提示消息
    private Object data;// 数据对象



}
