package service;

import com.github.pagehelper.PageInfo;
import dto.PageResult;
import dto.RequestDTO;
import dto.ReturnData;
import org.apache.poi.ss.formula.functions.T;
import pojo.Merchant;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/29 16:13
 */
public interface MerchantService {

    /**
     * @Description: 分页查询
     * @Author: LxH
     * @Date: 2020/6/29 16:50
     */
    PageResult<Merchant> getPageByMerchant(Merchant merchant, RequestDTO<T> requestDTO);

    /**
     * @Description: 添加商户
     * @Author: LxH
     * @Date: 2020/6/29 18:00
     */
    ReturnData<Boolean> addMerchant(Merchant merchant);
}
