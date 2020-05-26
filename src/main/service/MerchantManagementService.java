package service;


import dto.RequestDTO;
import dto.ReturnData;
import pojo.Rebate;
import pojo.User;
import vo.Page;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/25 11:09
 */
public interface MerchantManagementService {

    /**
     * @Description: 分页查询
     * @Author: LxH
     * @Date: 2020/5/25 14:08
     */
    Page<User> getMerchantManagementPageByParam(RequestDTO<User> requestDTO,User user);

    /**
     * @Description: 添加商户
     * @Author: LxH
     * @Date: 2020/5/26 15:29
     */
    ReturnData addMerchant(User user);

    /**
     * @Description: 修改商家信息
     * @Author: LxH
     * @Date: 2020/5/26 15:49
     */
    ReturnData updateMerchant(User user);

    /**
     * @Description: 删除商户
     * @Author: LxH
     * @Date: 2020/5/26 15:59
     */
    ReturnData deleteMerchantById(Integer[] userIds);
}
