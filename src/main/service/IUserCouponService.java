package service;

import dto.RequestDTO;
import dto.ReturnData;
import pojo.AppFeedback;
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


    /**
     * 添加用户红包
     * @param userCoupon
     * @return
     */
    ReturnData<Boolean> add(UserCoupon userCoupon) ;

    /**
     * 修改用户红包
     * @param userCoupon
     * @return
     */
    ReturnData<Boolean> update(UserCoupon userCoupon) ;
}
