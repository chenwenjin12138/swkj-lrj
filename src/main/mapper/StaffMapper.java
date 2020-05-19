package mapper;

import pojo.user.AppStaff;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/16 14:22
 */
public interface StaffMapper extends Mapper<AppStaff>, MySqlMapper<AppStaff> {
}
