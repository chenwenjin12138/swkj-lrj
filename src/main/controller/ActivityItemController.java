package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pojo.AppFeedback;
import pojo.activity.ActivityItem;
import service.IActivityItemService;
import service.IAppFeedbackService;

/**
 * @author : fl
 * @describe : 增值服务管理控制类
 * @date : 2020-5-13
 */
@RestController
@RequestMapping("/activityItem")
@AllArgsConstructor
@Api(tags = "活动详情管理")
public class ActivityItemController {
    private IActivityItemService activityItemService;

    @PostMapping("/add")
    @ApiOperation(value = "增加活动详情")
    public ReturnData<Boolean> add(ActivityItem activityItem){
        return  activityItemService.add(activityItem);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除活动详情")
    public ReturnData<Boolean> delete(@PathVariable int id){
        return activityItemService.delete(id);
    }

}
