package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.OrderComment;
import pojo.user.AppUser;
import service.IAppUserService;
import service.IOrderCommentService;

/**
 * @author : fl
 * @describe : app用户评论管理控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/orderComment")
@AllArgsConstructor
@Api(tags = "app用户评论管理")
public class OrderCommentController {
    private IOrderCommentService iOrderCommentService;
    /**
     * 分页查询所有app用户
     * @return
     */
    @PostMapping("/orderCommentPageByParam")
    @ApiOperation("查询用户评论")
    public PageInfo<OrderComment> orderCommentPageByParam(@RequestBody RequestDTO requestDTO){
       return iOrderCommentService.getOrderCommentPageByParam(requestDTO);
    }

    /**
     * 修改app用户评论
     * @return
     */
    @PostMapping("/updateOrder")
    @ApiOperation(value = "修改app用户评论",notes = "显示用户评论：isVisible 属性传1")
    public ReturnData<Boolean> updateOrder(@RequestBody OrderComment orderComment){
        return iOrderCommentService.updateOrder(orderComment);
    }


}
