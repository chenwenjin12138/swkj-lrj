package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.OrderCommentMapper;
import org.springframework.stereotype.Service;
import pojo.OrderComment;
import service.IOrderCommentService;

import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.OrderComment.ID_COLUMN;

/**
 * @author fl
 * @descrip: 用户评论管理
 * @date 2020/5/12 0012下午 5:16
 */
@Service
public class OrderCommentServiceImpl implements IOrderCommentService {
    private OrderCommentMapper commentMapper;
    private ObjectMapper objectMapper;

    public OrderCommentServiceImpl(OrderCommentMapper commentMapper, ObjectMapper objectMapper) {
        this.commentMapper = commentMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public PageInfo<OrderComment> getOrderCommentPageByParam(RequestDTO requestDTO) {
        QueryWrapper<OrderComment> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(requestDTO.getBeginTime()) && StringUtils.isNotEmpty(requestDTO.getEndTime()) ) {
            queryWrapper.between(OrderComment.CREATE_TIME_COLUMN,requestDTO.getBeginTime(),requestDTO.getEndTime());
        }
        queryWrapper.orderByDesc(OrderComment.CREATE_TIME_COLUMN);
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<OrderComment> list = commentMapper.selectList(queryWrapper);
        return new PageInfo<OrderComment>(list);
    }

    @Override
    public ReturnData<Boolean> updateOrder(OrderComment order) {
        UpdateWrapper<OrderComment> updateWrapper = new UpdateWrapper<OrderComment>();
        updateWrapper.eq(ID_COLUMN, order.getOrderCommentId());
        if (commentMapper.update(order, updateWrapper) > 0 ) {
            return new ReturnData(SUCCESS_CODE,"操作成功", true);
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }
}
