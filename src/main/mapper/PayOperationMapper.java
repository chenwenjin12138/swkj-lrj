package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pojo.AppPush;
import pojo.PayOperation;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.math.BigDecimal;

/**
 * @author : fl
 * @describe :流水的映射接口
 * @date : 2020-4-29
 */
public interface PayOperationMapper extends BaseMapper<PayOperation> , Mapper<PayOperation>, MySqlMapper<PayOperation> {

    @Select("SELECT sum(total_fee) from pay_operation WHERE user_id =#{userId} and check_status = 1")
    BigDecimal getTotalWithDraw(int userId);
}
