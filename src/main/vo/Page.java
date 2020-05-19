package vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import	java.util.List;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/15 21:28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -4900711726589195897L;
    private List<T> list;
    private Integer count;
}
