package service.impl;

import com.github.pagehelper.PageInfo;
import dto.PageResult;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.MerchantMapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import pojo.Merchant;
import service.MerchantService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;


import java.util.List;

import static dto.PageResult.SUCCESS_CODE;
import static pojo.Merchant.*;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/29 16:14
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;

    /**
     * @param： merchant
     * @param： requestDTO
     * @Description: 分页查询
     * @Author: LxH
     * @Date: 2020/6/29 16:50
     */
    @Override
    public PageResult<Merchant> getPageByMerchant(Merchant merchant, RequestDTO<T> requestDTO) {

        int start = (requestDTO.getPage()-1) * requestDTO.getSize();
        RowBounds rowBounds = new RowBounds(start, requestDTO.getSize());
        Example example = new Example(Merchant.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(COLUMN_MERCHANT_ACTIVE,ACTIVE_TRUE);
        if (merchant.getName()!=null) {
            criteria.andLike(COLUMN_MERCHANT_NAME,"%"+merchant.getName()+"%");
        }
        if (merchant.getPhone()!=null) {
            criteria.andLike(COLUMN_MERCHANT_PHONE,"%"+merchant.getPhone()+"%");
        }
        if (merchant.getType()!=null) {
            criteria.andEqualTo(COLUMN_MERCHANT_TYPE,merchant.getType());
        }
        List<Merchant> merchants = merchantMapper.selectByExampleAndRowBounds(example, rowBounds);
        int count = merchantMapper.selectCount(new Merchant());
        return new PageResult<>(SUCCESS_CODE, "查询成功", count, merchants);
    }

    /**
     * @param： merchant
     * @Description: 添加商户
     * @Author: LxH
     * @Date: 2020/6/29 18:00
     */
    @Override
    public ReturnData<Boolean> addMerchant(Merchant merchant) {

        return null;
    }
}
