package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import mapper.RebateMapper;
import org.springframework.stereotype.Service;
import pojo.Rebate;
import pojo.Reservation;
import service.RebateService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RebateServiceImpl implements RebateService {
    private RebateMapper rebateMapper;
    private ObjectMapper objectMapper;

    public RebateServiceImpl(RebateMapper rebateMapper, ObjectMapper objectMapper) {
        this.rebateMapper = rebateMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public PageInfo<Rebate> getPageByParam(RequestDTO requestDTO) {
        List<Rebate> list = this.getListByParam(requestDTO);
        return new PageInfo<Rebate>(list);
    }

    @Override
    public List<Rebate> getListByParam(RequestDTO requestDTO) {
        QueryWrapper<Rebate> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc(Rebate.COLUMN_CREATE_TIME1);
        try {
            Rebate  rebate = objectMapper.convertValue(requestDTO.getData(), Rebate.class);
            if (rebate != null && rebate.getUserId() != null) {
                queryWrapper.eq(Rebate.COLUMN_USER_ID1, rebate.getUserId());
            }
            if (requestDTO.getStartLocalDateTime() != null && requestDTO.getEndLocalDateTime() != null) {
                queryWrapper.between(Reservation.COLUMN_CREATE_TIME1,requestDTO.getStartLocalDateTime(),requestDTO.getEndLocalDateTime());
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return rebateMapper.selectList(queryWrapper);
    }

    @Override
    public BigDecimal getTotalRebate(int userId) {
        return rebateMapper.getTotalBackMoney(userId);
    }
}
