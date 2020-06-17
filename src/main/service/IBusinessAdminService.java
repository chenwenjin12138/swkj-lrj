package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.user.SysAdmin;

/**
 * @author : fl
 * @describe : 商家管理
 * @date : 2020-4-28
 */
public interface IBusinessAdminService {

    /**
     * 根据条件查询商家信息
     * @param RequestDTO 查询条件
     * @return
     */
    ReturnData<PageInfo<SysAdmin>> getBusinessAdminPageByParam(RequestDTO RequestDTO);

    /**
     * 修改商家信息
     * @param businessAdmin
     * @return
     */
    ReturnData<Boolean> updateBusinessAdmin(SysAdmin businessAdmin);

    /**
     * 添加商家信息
     * @param businessAdmin
     * @return
     */
    ReturnData<Boolean> addBusinessAdmin(SysAdmin businessAdmin);
}
