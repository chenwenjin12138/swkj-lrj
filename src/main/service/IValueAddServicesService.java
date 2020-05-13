package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.AppPush;
import pojo.ValueAddedServices;

import java.util.List;

/**
 * @author : fl
 * @describe : 增值服务
 * @date : 2020-5-13
 */
public interface IValueAddServicesService {

    /**
     * 获取推送消息
     * @param requestDTO 查询条件
     * @return
     */
    List<ValueAddedServices> getListByParam(RequestDTO requestDTO);

    /**
     * 添加
     * @param valueAddedServices
     * @return
     */

    ReturnData<Boolean> add(ValueAddedServices valueAddedServices) ;

    /**
     * 修改
     * @param valueAddedServices
     * @return
     */

    ReturnData<Boolean> update(ValueAddedServices valueAddedServices) ;

    /**
     * 删除
     * @param valueAddedServices
     * @return
     */
    ReturnData<Boolean> delete(ValueAddedServices valueAddedServices);


}
