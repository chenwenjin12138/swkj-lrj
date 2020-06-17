package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pojo.Rebate;

import java.math.BigDecimal;

/**
 * @author : fl
 * @describe :返利的映射接口
 * @date : 2020-4-29
 */
public interface RebateMapper extends BaseMapper<Rebate> {

    @Select("select sum(back_money) from app_rebate where user_id =#{userId}")
    BigDecimal getTotalBackMoney(int userId);

}
