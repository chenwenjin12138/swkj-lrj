package service.impl;
import mapper.IUserInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pojo.SysAuthority;
import pojo.UserInfo;
import service.IUserInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : cwj
 * @describe : 用户服务层实现
 * @date : 2020-3-18
 */
@Component
@Repository
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private IUserInfoMapper userInfoMapper;
    @Override
    public UserInfo getUserInfoByLoginInfo(String userPhone) {
        return userInfoMapper.getUserInfoByLoginInfo(userPhone);
    }

    @Override
    public List<SysAuthority> getAdminPermissionList(Long adminId) {
        return null;
    }
}
