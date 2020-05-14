package service;

import dto.RequestDTO;
import pojo.UserCoupon;

import java.util.List;

/**
 * @author : fl
 * @describe : 用户红包
 * @date : 2020-5-14
 */
public interface IUserCouponService {

    /**
     * 查询
     * @param requestDTO 查询条件
     * @return
     */
    List<UserCoupon> getListByParam(RequestDTO requestDTO);



}
