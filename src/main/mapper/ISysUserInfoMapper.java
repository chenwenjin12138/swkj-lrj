package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import pojo.UserInfo;

import java.util.List;

/**
 * @author : cwj
 * @describe : ISysUserInfoMapper的映射接口
 * @date : 2020-3-18
 */
@Repository
public interface ISysUserInfoMapper  {

    /**
     * 查询用户
     * @param userName
     * @return
     */
    @Select("select * from sys_admin where admin_name=#{userName}")
    SysUser getUserInfoByLoginInfo(@Param("userName") String userName);



}
