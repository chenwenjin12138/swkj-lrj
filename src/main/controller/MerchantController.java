package controller;

import com.github.pagehelper.PageInfo;
import dto.PageResult;
import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Merchant;
import service.MerchantService;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/29 16:11
 */
@RestController
@AllArgsConstructor
@Api(tags = "商家管理")
public class MerchantController {

    @Resource
    private MerchantService merchantService;

    /**
     * @Description: 分页查询
     * @Author: LxH
     * @Date: 2020/6/29 16:49
     */
    @PostMapping("getPageByMerchant")
    @ApiOperation(value = "分页查询",notes = "条件查询")
    public PageResult<Merchant> getPageByMerchant(Merchant merchant, RequestDTO<T> requestDTO){
        return merchantService.getPageByMerchant(merchant,requestDTO);
    }

    /**
     * @Description: 添加商户
     * @Author: LxH
     * @Date: 2020/6/29 18:00
     */
    @PostMapping("addMerchant")
    @ApiOperation(value = "添加商户")
    public ReturnData<Boolean> addMerchant(Merchant merchant){
        return merchantService.addMerchant(merchant);
    }
}
