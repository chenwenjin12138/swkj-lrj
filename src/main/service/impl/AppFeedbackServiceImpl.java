package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.AppFeedBackMapper;
import org.springframework.stereotype.Service;
import pojo.AppFeedback;
import service.IAppFeedbackService;

import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.AppFeedback.CONTACT_COLUMN;
import static pojo.AppPush.CREATE_TIME_COLUMN;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/20 0020上午 9:50
 */
@Service
public class AppFeedbackServiceImpl implements IAppFeedbackService {

    private AppFeedBackMapper appFeedBackMapper;
    private ObjectMapper objectMapper;

    public AppFeedbackServiceImpl(AppFeedBackMapper appFeedBackMapper, ObjectMapper objectMapper) {
        this.appFeedBackMapper = appFeedBackMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public PageInfo<AppFeedback> getPageByParam(RequestDTO requestDTO) {
        QueryWrapper<AppFeedback> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        AppFeedback feedback = objectMapper.convertValue(requestDTO.getData(), AppFeedback.class);
        if (feedback != null && StringUtils.isNotEmpty(feedback.getContact())) {
            queryWrapper.like(CONTACT_COLUMN, feedback.getContact());
        }
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<AppFeedback> list = appFeedBackMapper.selectList(queryWrapper);
        return new PageInfo<AppFeedback>(list);
    }

    @Override
    public ReturnData<Boolean> add(AppFeedback feedback) {
        try {
            if (appFeedBackMapper.insert(feedback) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }

    @Override
    public ReturnData<Boolean> delete(int id) {
        try {
            if (appFeedBackMapper.deleteById(id) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }
}
