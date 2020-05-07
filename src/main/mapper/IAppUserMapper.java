package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pojo.user.AppUser;

import java.util.List;

/**
 * @author : fl
 * @describe :APP用户管理的映射接口
 * @date : 2020-4-29
 */
public interface IAppUserMapper extends BaseMapper<AppUser> {

}
