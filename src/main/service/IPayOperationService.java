package service;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import net.bytebuddy.asm.Advice;
import pojo.PayOperation;
import pojo.user.AppStaff;

import java.math.BigDecimal;

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

    /**
     * 商户提现
     * @return
     */
    ReturnData<Boolean> add(PayOperation payOperation);

    /**
     * 退款提现处理
     * @return
     */
    ReturnData<Boolean> withdraw(PayOperation payOperation);

    BigDecimal getTotalWithDraw(int userId);


}
