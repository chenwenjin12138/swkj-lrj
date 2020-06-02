package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import dto.RequestDTO;
import mapper.UserCouponMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import pojo.SysCoupon;
import pojo.UserCoupon;
import pojo.user.AppStaff;
import service.IUserCouponService;
import sun.nio.cs.US_ASCII;

import java.util.List;

import static org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID;
import static pojo.AppPush.CREATE_TIME_COLUMN;

/**
 * @author fl
 * @descrip:用户红包实现类
 * @date 2020/5/14 0014上午 9:30
 */
@Service
public class UserCouponServiceImpl implements IUserCouponService {
    private UserCouponMapper userCouponMapper;
    private ObjectMapper objectMapper;

    public UserCouponServiceImpl(UserCouponMapper userCouponMapper, ObjectMapper objectMapper) {
        this.userCouponMapper = userCouponMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<UserCoupon> getListByParam(RequestDTO requestDTO) {
        QueryWrapper<UserCoupon> queryWrapper = new QueryWrapper();
        UserCoupon userCoupon = objectMapper.convertValue(requestDTO.getObject(), UserCoupon.class);
        if (userCoupon != null && StringUtils.isNotEmpty(userCoupon.getUserId().toString())) {
            queryWrapper.eq(UserCoupon.USER_ID_COLUMN,userCoupon.getUserId());
        }
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        return userCouponMapper.selectList(queryWrapper);
    }
}
