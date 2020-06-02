package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/1 14:14
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_consignee")
public class Consignee implements Serializable {
    private static final long serialVersionUID = 5935908143109288256L;
    @Id
    private Integer appConsigneeId;
    private Integer userId;
    private String consigneeName;
    private String consigneeMobile;
    private String name;
    private String address;
}
