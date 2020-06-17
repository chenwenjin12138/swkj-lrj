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
import pojo.PayOperation;
import pojo.user.AppStaff;
import service.IAppStaffService;
import service.IPayOperationService;


/**
 * @author : fl
 * @describe : 支付流水控制类
 * @date : 2020-4-27
 */
@RestController
@RequestMapping("/payOperation")
@AllArgsConstructor
@Api("用户流水记录（支付，退款，提现）")
public class PayOperationController {

    private IPayOperationService payOperationService;

    /**
     * 分页查询所有支付流水
     * @return
     */
    @PostMapping("/payOperation")
    @ApiOperation("分页查询所有支付流水")
    public PageInfo<PayOperation> payOperation(@RequestBody RequestDTO requestDTO){
        return payOperationService.getPageByParam(requestDTO);
    }



}
