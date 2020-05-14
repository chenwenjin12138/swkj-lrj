package service;

import dto.RequestDTO;
import dto.ReturnData;
import pojo.SysCoupon;
import pojo.SysRole;
import pojo.ValueAddedServices;

import java.util.List;

/**
 * @author : fl
 * @describe : 增值服务
 * @date : 2020-5-13
 */
public interface ISysCouponService {

    /**
     * 查询
     * @param requestDTO 查询条件
     * @return
     */
    List<SysCoupon> getListByParam(RequestDTO requestDTO);

    /**
     * 添加
     * @param sysCoupon
     * @return
     */

    ReturnData<Boolean> add(SysCoupon sysCoupon) ;

    /**
     * 修改
     * @param sysCoupon
     * @return
     */

    ReturnData<Boolean> update(SysCoupon sysCoupon) ;



}
