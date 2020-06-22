package dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vo.Local;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : fl
 * @describe : 分页查询参数类
 * @date : 2020-4-30
 */
@Getter
@Setter
@Data
@Accessors(chain = true)
@ApiModel("查询参数")
public class RequestDTO<E> {
    /**
     *查询参数实体
     */
    @ApiModelProperty(value = "查询对象参数")
    E data;

    /**
     * 查询页
     */
    @ApiModelProperty(value = "页数",required = true)
    Integer page=1;

    /**
     * 条数
     */
    @ApiModelProperty(value = "页数")
    Integer size=15;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间，结束时间对应endTime字段")
    String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    String endTime;

    @ApiModelProperty(value = "开始时间，结束时间对应endLocalDateTime")
    LocalDateTime startLocalDateTime;
    @ApiModelProperty(value = "结束时间")
    LocalDateTime endLocalDateTime;

}
