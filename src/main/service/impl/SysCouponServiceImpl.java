package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.SysCouponMapper;
import org.springframework.stereotype.Service;
import pojo.SysCoupon;
import service.ISysCouponService;

import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.AppPush.CREATE_TIME_COLUMN;
import static pojo.SysCoupon.ID_COLUMN;

/**
 * @author fl
 * @descrip: 红包类型管理类
 * @date 2020/5/13 0013下午 5:30
 */
@Service
public class SysCouponServiceImpl implements ISysCouponService {
    private SysCouponMapper sysCouponMapper;

    public SysCouponServiceImpl(SysCouponMapper sysCouponMapper) {
        this.sysCouponMapper = sysCouponMapper;
    }

    @Override
    public List<SysCoupon> getListByParam(RequestDTO requestDTO) {
        QueryWrapper<SysCoupon> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        return sysCouponMapper.selectList(queryWrapper);
    }

    @Override
    public ReturnData<Boolean> add(SysCoupon sysCoupon) {
        try {
            if (sysCouponMapper.insert(sysCoupon) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }


    @Override
    public ReturnData<Boolean> update(SysCoupon sysCoupon) {
        UpdateWrapper<SysCoupon> updateWrapper = new UpdateWrapper<SysCoupon>();
        updateWrapper.eq(ID_COLUMN, sysCoupon.getSysCouponId());
        try {
            if (sysCouponMapper.update(sysCoupon,updateWrapper)> 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }
}
