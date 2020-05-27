package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pojo.AppFeedback;
import pojo.activity.ActivityVo;
import service.IActivityService;
import service.IAppFeedbackService;

/**
 * @author : fl
 * @describe : 活动管理控制类
 * @date : 2020-5-26
 */
@RestController
@RequestMapping("/activity")
@AllArgsConstructor
@Api(tags = "活动管理")
public class ActivityController {
    private IAppFeedbackService appFeedbackService;
    private IActivityService activityService;

    /**
     * 查询活动
     * @param requestDTO 查询条件
     * @return
     */
    @PostMapping("/getPageByParam")
    @ApiOperation(value = "查询活动",notes = "查询条件：活动名称")
   public PageInfo<ActivityVo> getPageByParam(@RequestBody RequestDTO requestDTO){
        return activityService.getPageByParam(requestDTO);
    }

    /**
     * 添加
     * @param activityVo
     * @return
     */

    @PostMapping("/update")
    @ApiOperation(value = "增加修改删除活动")
    public ReturnData<Boolean> add(ActivityVo activityVo){
        return  activityService.add(activityVo);
    }



}
