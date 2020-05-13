package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
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
public class OrderCommentController {
    private IOrderCommentService iOrderCommentService;
    /**
     * 分页查询所有app用户
     * @return
     */
    @PostMapping("/orderCommentPageByParam")
    public PageInfo<OrderComment> orderCommentPageByParam(@RequestBody RequestDTO requestDTO){
       return iOrderCommentService.getOrderCommentPageByParam(requestDTO);
    }

    /**
     * 修改app用户
     * @return
     */
    @PostMapping("/updateOrder")
    public ReturnData<Boolean> updateOrder(@RequestBody OrderComment orderComment){
        return iOrderCommentService.updateOrder(orderComment);
    }


}
