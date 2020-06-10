package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pojo.AppPush;
import pojo.PayOperation;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author : fl
 * @describe :流水的映射接口
 * @date : 2020-4-29
 */
public interface PayOperationMapper extends BaseMapper<PayOperation> , Mapper<PayOperation>, MySqlMapper<PayOperation> {

}
