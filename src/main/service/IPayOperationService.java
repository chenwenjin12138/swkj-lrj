package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import pojo.PayOperation;
import pojo.user.AppStaff;

/**
 * @author : fl
 * @describe : 支付流水
 * @date : 2020-5-14
 */
public interface IPayOperationService {

    /**
     * 根据条件查询流水信息
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<PayOperation> getPageByParam(RequestDTO requestDTO);

}
