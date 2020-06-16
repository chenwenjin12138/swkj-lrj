package service;


import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import pojo.Rebate;

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

}
