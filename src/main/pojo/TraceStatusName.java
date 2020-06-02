package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 订单追踪状态名称
 * @Author Lxh
 * @Date 2020/6/1 10:36
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_trace_status")
public class TraceStatusName extends Base{
    private static final long serialVersionUID = 9153978028379635916L;
    @Id
    private Integer appTraceStatusId;
    private String traceStatusName;
    private Byte type;
    private Byte active;
}
