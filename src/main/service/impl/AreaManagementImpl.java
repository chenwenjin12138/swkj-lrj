package service.impl;

import dto.ReturnData;
import mapper.AreaManagementMapper;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.stereotype.Service;
import pojo.AreaManagement;
import service.AreaManagementService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/27 15:13
 */
@Service
public class AreaManagementImpl implements AreaManagementService {

    @Resource
    private AreaManagementMapper areaManagementMapper;

    private ReturnData returnData = new ReturnData();

    /**
     * @param: itemId
     * @Description: 根据商品查询面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:33
     */
    @Override
    public List<AreaManagement> findAreaByItemId(Integer itemId) {
        Example example = new Example(AreaManagement.class);
        example.createCriteria().andEqualTo("itemId", itemId);
        return areaManagementMapper.selectByExample(example);
    }

    /**
     * @param: itemId
     * @Description: 根据商品添加面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:39
     */
    @Override
    public ReturnData addAreaByItemId(AreaManagement areaManagement) {
        if (areaManagementMapper.insertSelective(areaManagement)>0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("添加成功").setObject(null);
        }
        return returnData.setCode(Fail_CODE).setMessage("添加失败").setObject(null);
    }

    /**
     * @param: areaManagement
     * @Description: 根据商品修改面积信息
     * @Author: LxH
     * @Date: 2020/5/27 15:52
     */
    @Override
    public ReturnData updateAreaByItemId(AreaManagement areaManagement) {
        if (areaManagementMapper.updateByPrimaryKeySelective(areaManagement)>0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("修改成功").setObject(null);
        }
        return returnData.setCode(Fail_CODE).setMessage("修改失败").setObject(null);
    }

    /**
     * @param: areaManagementIds
     * @Description: 根据面积主键删除面积信息
     * @Author: LxH
     * @Date: 2020/5/27 16:00
     */
    @Override
    public ReturnData deleteAreaByItemIds(Integer[] areaManagementIds) {
        for (Integer areaManagementId : areaManagementIds) {
            areaManagementMapper.deleteByPrimaryKey(areaManagementId);
        }
        return returnData.setCode(SUCCESS_CODE).setMessage("删除成功").setObject(null);
    }

    /**
     * @Description: 获取基础价格
     * @Author: LxH
     * @Date: 2020/5/28 10:13
     */
    @Override
    public List<AreaManagement> findBasisArea() {
        Example example = new Example(AreaManagement.class);
        example.createCriteria().andEqualTo("itemId",388);
        return areaManagementMapper.selectByExample(example);
    }

}
