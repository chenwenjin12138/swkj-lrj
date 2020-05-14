package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import mapper.PayOperationMapper;
import org.springframework.stereotype.Service;
import pojo.PayOperation;
import service.IPayOperationService;

import java.util.List;

import static pojo.PayOperation.TRADE_SOURCE_COLUMN;
import static pojo.PayOperation.TRADE_TYPE_COLUMN;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/14 0014下午 2:24
 */
@Service
public class PayOperationServiceImpl implements IPayOperationService {
    private PayOperationMapper payOperationMapper;
    private ObjectMapper objectMapper;

    public PayOperationServiceImpl(PayOperationMapper payOperationMapper, ObjectMapper objectMapper) {
        this.payOperationMapper = payOperationMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public PageInfo<PayOperation> getPageByParam(RequestDTO requestDTO) {
        QueryWrapper<PayOperation> queryWrapper = new QueryWrapper();
        PayOperation payOperation = objectMapper.convertValue(requestDTO.getObject(), PayOperation.class);
        if (payOperation != null && StringUtils.isNotEmpty(payOperation.getTradeSource().toString())) {
            queryWrapper.eq(TRADE_SOURCE_COLUMN, payOperation.getTradeSource());
        }
        if (payOperation != null && StringUtils.isNotEmpty(payOperation.getTradeType().toString())) {
            queryWrapper.eq(TRADE_TYPE_COLUMN, payOperation.getTradeSource());
        }
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<PayOperation> list = payOperationMapper.selectList(queryWrapper);
        return new PageInfo<PayOperation>(list);
    }
}
