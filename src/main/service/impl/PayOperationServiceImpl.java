package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.PayOperationMapper;
import mapper.RebateMapper;
import org.springframework.stereotype.Service;
import pojo.*;
import pojo.activity.ActivityItem;
import pojo.activity.ActivityTime;
import service.IBalanceService;
import service.IPayOperationService;
import service.RebateService;
import util.DateUtil;
import util.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.PayOperation.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/14 0014下午 2:24
 */
@Service
public class PayOperationServiceImpl implements IPayOperationService {
    private PayOperationMapper payOperationMapper;
    private ObjectMapper objectMapper;
    private RebateService rebateService;
    private RebateMapper rebateMapper;

    public PayOperationServiceImpl(PayOperationMapper payOperationMapper, ObjectMapper objectMapper, RebateService rebateService, RebateMapper rebateMapper) {
        this.payOperationMapper = payOperationMapper;
        this.objectMapper = objectMapper;
        this.rebateService = rebateService;
        this.rebateMapper = rebateMapper;
    }

    @Override
    public PageInfo<PayOperation> getPageByParam(RequestDTO requestDTO) {
        QueryWrapper<PayOperation> queryWrapper = new QueryWrapper();
        try {
            PayOperation  payOperation = objectMapper.convertValue(requestDTO.getObject(), PayOperation.class);
            if (payOperation != null) {
                if (StringUtils.isNotEmpty(payOperation.getTradeSource().toString())) {
                    queryWrapper.eq(TRADE_SOURCE_COLUMN, payOperation.getTradeSource());
                }
                if (StringUtils.isNotEmpty(payOperation.getTradeType().toString())) {
                    queryWrapper.eq(TRADE_TYPE_COLUMN, payOperation.getTradeType());
                }
                if (payOperation.getCheckStatus() != null) {
                    queryWrapper.eq(CHECK_STATUS_COLUMN, payOperation.getCheckStatus());
                }
                if (StringUtils.isNotEmpty(payOperation.getUserPhone())) {
                    queryWrapper.like(USER_PHONE_COLUMN, payOperation.getUserPhone());
                }
            }
            if (requestDTO.getStartLocalDateTime() != null && requestDTO.getEndLocalDateTime() != null) {
                    queryWrapper.between(Reservation.COLUMN_CREATE_TIME1,requestDTO.getStartLocalDateTime(),requestDTO.getEndLocalDateTime());
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<PayOperation> list = payOperationMapper.selectList(queryWrapper);
        return new PageInfo<PayOperation>(list);
    }

    @Override
    public ReturnData<Boolean> add(PayOperation payOperation) {
        try {
            payOperation.setCreateTime(LocalDateTime.now());
            if (payOperationMapper.insert(payOperation) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> withdraw(PayOperation payOperation) {
        if (payOperation.getUserId() == null || payOperation.getTotalFee() == null) {
            return new ReturnData(Fail_CODE,"操作失败,用户id和金额不能为空",false);
        }
        BigDecimal withdraw = payOperationMapper.getTotalWithDraw(payOperation.getUserId());
        BigDecimal backMonty = rebateMapper.getTotalBackMoney(payOperation.getUserId());
        if (backMonty == null ) {
            return new ReturnData(Fail_CODE,"沒有返利，不能提现",false);
        }else{
            if (withdraw == null && backMonty.compareTo(payOperation.getTotalFee()) < 0) {
                return new ReturnData(Fail_CODE,"提现金额大于返利金额",false);
            }
            if (withdraw != null && backMonty.subtract(withdraw).compareTo(payOperation.getTotalFee())<0) {
                return new ReturnData(Fail_CODE,"提现金额大于返利金额",false);
            }
        }
        UpdateWrapper<PayOperation> updateWrapper = new UpdateWrapper<PayOperation>();
        try {
            updateWrapper.eq(ActivityTime.ID_COLUMN,payOperation.getId());
            if (payOperationMapper.update(payOperation,updateWrapper)> 0) {
                //TODO 提现退款记录处理后通知用户
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public BigDecimal getTotalWithDraw(int userId) {
        return payOperationMapper.getTotalWithDraw(userId);
    }
}
