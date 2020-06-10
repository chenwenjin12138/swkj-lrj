package pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lxh
 * @date 2020/3/24 10:16
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_item_category")
@TableName("app_item_category")
public class AppItemCat implements Serializable{
    private static final long serialVersionUID = -226125165955136009L;
    @Id
    private Integer appItemCategoryId;
    private String categoryName;
    private String categroryPic;
    private String pid;
    private Integer isShow;
    private Integer categoryType;

    public static final String ID_COLUMN = "app_item_category_id";
}
