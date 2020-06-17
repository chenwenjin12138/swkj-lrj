package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.Constant;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.ActivityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import pojo.AppItemCat;
import pojo.activity.*;
import service.IActivityItemService;
import service.IActivityService;
import service.IActivityTimeService;
import service.IAppItemCatService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.AppPush.CREATE_TIME_COLUMN;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/25 0025下午 3:10
 */
@Service
public class ActivityServiceImpl implements IActivityService {
    private ActivityMapper activityMapper;
    private ObjectMapper objectMapper;
    private IActivityItemService activityItemService;
    private IActivityTimeService activityTimeService;
    private IAppItemCatService appItemCatService;

    public ActivityServiceImpl(ActivityMapper activityMapper, ObjectMapper objectMapper, IActivityItemService activityItemService, IActivityTimeService activityTimeService) {
        this.activityMapper = activityMapper;
        this.objectMapper = objectMapper;
        this.activityItemService = activityItemService;
        this.activityTimeService = activityTimeService;
    }

    @Override
    public PageInfo<ActivityVo> getPageByParam(RequestDTO requestDTO) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        queryWrapper.eq(Activity.DELETED_COLUMN, Constant.NOT_DELETED);
            try {
                ActivityVo activityVo = objectMapper.convertValue(requestDTO.getData(), ActivityVo.class);
                if (activityVo != null && activityVo.getActivity()!=null) {
                    Activity activity = activityVo.getActivity();
                    if (StringUtils.isNotEmpty(activity.getName())) {
                        queryWrapper.like(Activity.NAME_COLUMN,activity.getName());
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<Activity> list = activityMapper.selectList(queryWrapper);
        PageInfo<Activity> activityPageInfo =new PageInfo<Activity>(list);
        List<ActivityVo>returnList = new ArrayList<ActivityVo>();
        List<ActivityItemVO> activityItemVOList = new ArrayList<ActivityItemVO>();
        for (Activity activity:activityPageInfo.getList()) {
            List<ActivityItem> itemList = activityItemService.getListByParam(activity.getId());
            for (ActivityItem activityItem:itemList) {
                ActivityItemVO itemVO = new ActivityItemVO();
                itemVO.setActivityItem(activityItem);
                List<String> itemCatIds = Arrays.asList(activityItem.getAppItemCategoryId().split(","));
                List<AppItemCat> itemCatList = appItemCatService.findListByParam(itemCatIds);
                itemVO.setAppItemCatList(itemCatList);
                activityItemVOList.add(itemVO);
            }
            List<ActivityTime> timeList = activityTimeService.getListByParam(activity.getId());
            ActivityVo activityVo1 = new ActivityVo();
            activityVo1.setActivity(activity);
            activityVo1.setActivityTimeList(timeList);
            activityVo1.setActivityItemList(activityItemVOList);
            returnList.add(activityVo1);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(returnList);
        pageInfo.setSize(activityPageInfo.getSize());
        pageInfo.setTotal(activityPageInfo.getTotal());
        pageInfo.setPages(activityPageInfo.getPages());
        pageInfo.setPageSize(activityPageInfo.getPageSize());
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnData<Boolean> add(ActivityVo activityVo) {
        if (activityVo.getActivity() ==null) {
            return new ReturnData(Fail_CODE,"操作失败,参数不足",false );
        }
        Activity activity = activityVo.getActivity();
        try {
            int activityResult;
            if (activity.getId()>0) {
                activityResult = activityMapper.updateById(activity);
            }else{
                activityResult = activityMapper.insert(activity);
            }
            if (activityResult > 0) {
                if (activityVo.getActivityTimeList()==null || activityVo.getActivityTimeList().size()<1) {
                    return new ReturnData(Fail_CODE,"操作失败,参数不足",false );
                }
                for (ActivityTime activityTime:activityVo.getActivityTimeList()) {
                    activityTime.setActivityId(activity.getId());
                    ReturnData timeReturnData;
                    if (activityTime.getId()>0) {
                        timeReturnData =  activityTimeService.update(activityTime);
                    }else{
                        timeReturnData = activityTimeService.add(activityTime);
                    }
                    if(timeReturnData.getCode() != SUCCESS_CODE){
                        throw new RuntimeException("操作失败,添加时段异常");
                    }
                }
                if (activityVo.getActivityItemList()!= null || activityVo.getActivityItemList().size()>1) {
                    for (ActivityItemVO activityItemVO : activityVo.getActivityItemList()) {
                        ActivityItem activityItem = activityItemVO.getActivityItem();
                        activityItem.setActivityId(activity.getId());
                        ReturnData itemReturnData;
                        if (activityItem.getId()>0) {
                            itemReturnData =  activityItemService.update(activityItem);
                        }else{
                            itemReturnData = activityItemService.add(activityItem);
                        }
                        if (itemReturnData.getCode() != SUCCESS_CODE) {
                            throw new RuntimeException("操作失败,添加活动详情异常");
                        }
                    }
                }
                return new ReturnData(SUCCESS_CODE,"操作成功", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚事务
            System.out.println("异常消息："+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new ReturnData(Fail_CODE,"操作失败",false);
    }

    @Override
    public ReturnData<Boolean> update(Activity activity) {
        return null;
    }

    @Override
    public ReturnData<Boolean> delete(int id) {
        return null;
    }
}
