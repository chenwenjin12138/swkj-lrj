package pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import javax.persistence.*;


/**
 * @Description: Banner类
 * @Author Lxh
 * @Date 2020/5/12 15:49
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_banner")
public class Banner extends Base{
    private static final long serialVersionUID = 2967520038445665960L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appBannerId;
    private String bannerName;
    private String bannerDetails;
    private Integer bannerType;
    @Transient
    private String bannerTypeName;
    private String bannerImg;
    private String url;
    private Byte isShow;
    private Byte sortOrder;
}
