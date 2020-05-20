package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pojo.AppFeedback;
import service.IAppFeedbackService;

/**
 * @author : fl
 * @describe : 增值服务管理控制类
 * @date : 2020-5-13
 */
@RestController
@RequestMapping("/appFeedback")
@AllArgsConstructor
@Api(tags = "用户反馈管理")
public class AppFeedbackController {
    private IAppFeedbackService appFeedbackService;

    /**
     * 查找增值服务
     * @param requestDTO 查询条件
     * @return
     */
    @PostMapping("/getPageByParam")
    @ApiOperation(value = "查询用户反馈",notes = "查询条件：电话号码")
   public PageInfo<AppFeedback> getPageByParam(@RequestBody RequestDTO requestDTO){
        return appFeedbackService.getPageByParam(requestDTO);
    }

    /**
     * 添加
     * @param appFeedback
     * @return
     */

    @PostMapping("/add")
    @ApiOperation(value = "增加反馈")
    public ReturnData<Boolean> add(AppFeedback appFeedback){
        return  appFeedbackService.add(appFeedback);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除反馈")
    public ReturnData<Boolean> delete(@PathVariable int id){
        return appFeedbackService.delete(id);
    }


}
