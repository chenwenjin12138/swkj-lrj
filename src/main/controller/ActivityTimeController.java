package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pojo.AppFeedback;
import pojo.activity.ActivityTime;
import service.IActivityItemService;
import service.IActivityTimeService;
import service.IAppFeedbackService;

/**
 * @author : fl
 * @describe : 增值服务管理控制类
 * @date : 2020-5-13
 */
@RestController
@RequestMapping("/activityTime")
@AllArgsConstructor
@Api(tags = "活动时段管理")
public class ActivityTimeController {
    private IActivityTimeService activityTimeService;

    /**
     * 添加
     * @param activityTime
     * @return
     */

    @PostMapping("/add")
    @ApiOperation(value = "增加活动时段")
    public ReturnData<Boolean> add(ActivityTime activityTime){
        return  activityTimeService.add(activityTime);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除活动时段")
    public ReturnData<Boolean> delete(@PathVariable int id){
        return activityTimeService.delete(id);
    }


}
