package mapper;

import pojo.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/16 13:36
 */
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}
