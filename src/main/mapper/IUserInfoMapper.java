package mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pojo.UserInfo;

/**
 * @author : cwj
 * @describe : mapper的映射接口
 * @date : 2020-3-18
 */
@Repository
public interface IUserInfoMapper {
    UserInfo  getUserInfoByLoginInfo(String userPhone);
}
