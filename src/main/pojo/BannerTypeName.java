package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/13 17:15
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "banner_type_name")
public class BannerTypeName {
    @Id
    private String bannerTypeId;
    private String bannerTypeName;
}
