package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Description: 创建时间 更新时间
 * @Author Lxh
 * @Date 2020/5/8 9:37
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Base implements Serializable {
    private static final long serialVersionUID = -8228639198011278668L;
    private String createTime ;
    @Transient
    private String updateTime ;
}
