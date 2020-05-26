package mapper;

import pojo.Rebate;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/25 11:10
 */
public interface MerchantManagementMapper extends Mapper<Rebate>, MySqlMapper<Rebate> {
}
