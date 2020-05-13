package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.ValueAddServicesMapper;
import org.springframework.stereotype.Service;
import pojo.ValueAddedServices;
import service.IValueAddServicesService;

import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.AppPush.CREATE_TIME_COLUMN;
import static pojo.ValueAddedServices.VALUE_ADDED_SERVICES_ID_COLUMN;

/**
 * @author fl
 * @descrip: 增值服务实现类
 * @date 2020/5/13 0013下午 3:11
 */
@Service
public class ValueAddServicesServiceImpl  implements IValueAddServicesService {
    private ValueAddServicesMapper valueAddServicesMapper;

    public ValueAddServicesServiceImpl(ValueAddServicesMapper valueAddServicesMapper) {
        this.valueAddServicesMapper = valueAddServicesMapper;
    }

    @Override
    public List<ValueAddedServices> getListByParam(RequestDTO requestDTO) {
        QueryWrapper<ValueAddedServices> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        return valueAddServicesMapper.selectList(queryWrapper);
    }

    @Override
    public ReturnData<Boolean> add(ValueAddedServices valueAddedServices) {
        try {
            if (valueAddServicesMapper.insert(valueAddedServices) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> update(ValueAddedServices valueAddedServices) {
        UpdateWrapper<ValueAddedServices> updateWrapper = new UpdateWrapper<ValueAddedServices>();
        updateWrapper.eq(VALUE_ADDED_SERVICES_ID_COLUMN, valueAddedServices.getValueAddedServicesId());
        try {
            if (valueAddServicesMapper.update(valueAddedServices,updateWrapper)> 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> delete(int id) {
        QueryWrapper<ValueAddedServices> queryWrapper = new QueryWrapper();
        queryWrapper.eq(VALUE_ADDED_SERVICES_ID_COLUMN,id);
        try {
            if (valueAddServicesMapper.delete(queryWrapper) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }
}
