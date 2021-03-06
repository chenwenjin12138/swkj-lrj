package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.UserCouponMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import pojo.SysCoupon;
import pojo.UserCoupon;
import pojo.ValueAddedServices;
import pojo.user.AppStaff;
import service.ISysCouponService;
import service.IUserCouponService;
import sun.nio.cs.US_ASCII;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID;
import static pojo.AppPush.CREATE_TIME_COLUMN;
import static pojo.ValueAddedServices.VALUE_ADDED_SERVICES_ID_COLUMN;

/**
 * @author fl
 * @descrip:用户红包实现类
 * @date 2020/5/14 0014上午 9:30
 */
@Service
public class UserCouponServiceImpl implements IUserCouponService {
    private UserCouponMapper userCouponMapper;
    private ObjectMapper objectMapper;
    private ISysCouponService sysCouponService;

    public UserCouponServiceImpl(UserCouponMapper userCouponMapper, ObjectMapper objectMapper, ISysCouponService sysCouponService) {
        this.userCouponMapper = userCouponMapper;
        this.objectMapper = objectMapper;
        this.sysCouponService = sysCouponService;
    }

    @Override
    public List<UserCoupon> getListByParam(RequestDTO requestDTO) {
        QueryWrapper<UserCoupon> queryWrapper = new QueryWrapper();
        UserCoupon userCoupon = objectMapper.convertValue(requestDTO.getData(), UserCoupon.class);
        if (userCoupon != null && userCoupon.getUserId() != null && StringUtils.isNotEmpty(userCoupon.getUserId().toString())) {
            queryWrapper.eq(UserCoupon.USER_ID_COLUMN,userCoupon.getUserId());
        }
        if (userCoupon != null &&userCoupon.getActive() != null) {
            queryWrapper.eq(UserCoupon.ACTIVE,userCoupon.getActive());
        }
        if (userCoupon != null && userCoupon.getUseStatus() !=null) {
            queryWrapper.eq(UserCoupon.USE_STATUS,userCoupon.getUseStatus());
        }

        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        return userCouponMapper.selectList(queryWrapper);
    }

    @Override
    public ReturnData<Boolean> add(UserCoupon userCoupon) {
        try {
            if (userCoupon.getSysCouponId() != null) {
                RequestDTO requestDTO = new RequestDTO();
                SysCoupon sysCouponParam = new SysCoupon();
                sysCouponParam.setSysCouponId(userCoupon.getSysCouponId());
                requestDTO.setData(sysCouponParam);
                List<SysCoupon> list =sysCouponService.getListByParam(requestDTO);
                if (list == null || list.size()<1) {
                    return new ReturnData(Fail_CODE,"没有对应的红包类型,操作失败",false );
                }
                LocalDateTime localDateTime = LocalDateTime.now();
                userCoupon.setCreateTime(localDateTime);
                userCoupon.setLimitTime(localDateTime.plusDays(list.get(0).getAging()));
                if (userCoupon.getDenomination() == null || userCoupon.getDenomination().compareTo(BigDecimal.ZERO) == 0) {
                    userCoupon.setDenomination(list.get(0).getDenomination());
                }
            }
            if (userCouponMapper.insert(userCoupon) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }

    @Override
    public ReturnData<Boolean> update(UserCoupon userCoupon) {
        UpdateWrapper<UserCoupon> updateWrapper = new UpdateWrapper<UserCoupon>();
        updateWrapper.eq(UserCoupon.ID,userCoupon.getId());
        try {
            if (userCouponMapper.update(userCoupon, updateWrapper) > 0) {
                return new ReturnData(SUCCESS_CODE, "操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE, "操作失败", false);
    }
}
