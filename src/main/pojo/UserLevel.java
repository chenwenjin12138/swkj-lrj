package pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @author Lxh
 * @date 2020/4/30 15:10
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_level")
@ApiModel("用户等级")
public class UserLevel {
    @Id
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private Integer levelId;
    @ApiModelProperty("邀请人数")
    private Integer inviteNum;
}
