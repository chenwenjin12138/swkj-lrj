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
 * @Date 2020/5/15 10:46
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class User extends Base{
    private static final long serialVersionUID = 4614457906889718376L;
    @Id
    private Integer appUserId;
    private String userPhone;
    private String userPassword;
    private String nickName;
}
