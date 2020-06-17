package service;


import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import pojo.Rebate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : fl
 * @describe :用户反馈
 * @date : 2020-5-11
 */
public interface RebateService {

    /**
     * 获取返利
     * @param requestDTO 查询条件
     * @return
     */
    PageInfo<Rebate> getPageByParam(RequestDTO requestDTO);

    List<Rebate> getListByParam(RequestDTO requestDTO);

    /**
     * 返利总额
     * @param userId
     * @return
     */
    BigDecimal getTotalRebate(int userId);
}
