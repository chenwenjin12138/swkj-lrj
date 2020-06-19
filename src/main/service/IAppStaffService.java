package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.user.AppStaff;

/**
 * @author : fl
 * @describe : APP员工管理
 * @date : 2020-4-28
 */
public interface IAppStaffService {

    /**
     * 根据条件查询用户信息
     * @param RequestDTO 查询条件
     * @return
     */
    ReturnData<PageInfo<AppStaff>> getAppUserPageByParam(RequestDTO RequestDTO);

    /**
     * 修改App用户对象
     * @param appStaff
     * @return
     */
    ReturnData<Boolean> updateAppStaff(AppStaff appStaff);

    /**
     * 添加员工
     * @param appStaff
     * @return
     */
    ReturnData<Boolean> addAppStaff(AppStaff appStaff);
}
