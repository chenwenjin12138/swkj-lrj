package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lxh
 * @date 2020/3/24 10:16
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_item_category")
public class AppItemCategoryEntity {
    @Id
    private Integer appItemCategoryId;
    private String categoryName;
    private String categroryPic;
    private String pid;
    private Integer isShow;
}
