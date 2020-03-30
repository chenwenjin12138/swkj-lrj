package service.item.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author Lxh
 * @date 2020/3/24 11:28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemMessage {
    private Integer itemMessageId; // 自增主键
    private String itemCategoryName; // 商品类别
    private Integer itemCategoryId;// 商品类别id
    private String itemMessage = ""; // 商品类别描述
    private Integer active; // 是否启用：1.启用 0.禁用
    private String createTime = ""; // 创建时间
}
