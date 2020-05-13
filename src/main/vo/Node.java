package vo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/8 16:02
 */
@Data
@Accessors(chain = true)
public class Node implements Serializable {
    private static final long serialVersionUID = -8393780751608564866L;
    /**商品种类id*/
    private Integer appItemCategoryId;
    /**商品种类名称*/
    private String categoryName;
    private String pid;
}
