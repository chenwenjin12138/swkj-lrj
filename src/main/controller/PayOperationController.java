package controller;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
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
public class PayOperationController {

    private IPayOperationService payOperationService;

    /**
     * 分页查询所有支付流水
     * @return
     */
    @PostMapping("/payOperation")
    public PageInfo<PayOperation> getAppUser(@RequestBody RequestDTO requestDTO){
        return payOperationService.getPageByParam(requestDTO);
    }


}
