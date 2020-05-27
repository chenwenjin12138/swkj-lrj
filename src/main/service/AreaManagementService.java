package service;

import dto.ReturnData;
import pojo.AreaManagement;

import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/27 15:12
 */
public interface AreaManagementService {

    /**
     * @Description: 根据商品查询面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:33
     */
    List<AreaManagement> findAreaByItemId(Integer itemId);

    /**
     * @Description: 根据商品添加面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:39
     */
    ReturnData addAreaByItemId(AreaManagement areaManagement);

    /**
     * @Description: 根据商品修改面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:52
     */
    ReturnData updateAreaByItemId(AreaManagement areaManagement);

    /**
     * @Description: 根据面积主键删除面积信息
     * @Author: LxH
     * @Date: 2020/5/27 16:00
     */
    ReturnData deleteAreaByItemIds(Integer[] areaManagementIds);
}
