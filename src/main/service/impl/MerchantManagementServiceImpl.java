package service.impl;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.MerchantManagementMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Rebate;
import pojo.User;
import service.MerchantManagementService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;
import vo.Page;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.Rebate.*;
import static pojo.User.*;
import static pojo.User.COLUMN_CREATE_TIME;
import static pojo.User.COLUMN_TYPE;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/25 11:09
 */
@Service
public class MerchantManagementServiceImpl implements MerchantManagementService {

    @Resource
    private MerchantManagementMapper merchantManagementMapper;

    @Resource
    private UserMapper userMapper;

    private ReturnData<Object> returnData = new ReturnData();

    /**
     * @param: rebateRequestDTO
     * @Description: 分页查询
     * @Author: LxH
     * @Date: 2020/5/25 14:08
     */
    @Override
    public Page<User> getMerchantManagementPageByParam(RequestDTO<User> requestDTO,User user) {
        Example example = new Example(User.class);
        example.orderBy(COLUMN_CREATE_TIME);
        Example.Criteria criteria = example.createCriteria().andEqualTo(COLUMN_TYPE, COLUMN_TYPE_2);
        if (user.getNickName()!=null) {
            criteria.andLike(COLUMN_NICK_NAME,"%"+user.getNickName()+"%");
        }
        if (user.getUserPhone()!=null) {
            criteria.andEqualTo(COLUMN_USER_PHONE,user.getUserPhone());
        }
        if (user.getActive()!=null) {
            criteria.andEqualTo(COLUMN_ACTIVE,user.getActive());
        }
        int start = requestDTO.getSize() * requestDTO.getPage();
        RowBounds rowBounds = new RowBounds(start, requestDTO.getPage());
        List<User> users = userMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (User userNew : users) {
            Example e = new Example(Rebate.class);
            e.createCriteria().andEqualTo(COLUMN_USER_ID,userNew.getAppUserId());
            List<Rebate> rebates = merchantManagementMapper.selectByExample(e);
            for (Rebate rebate : rebates) {
                User name = userMapper.selectByPrimaryKey(rebate.getLowId());
                userNew.setBackMoney(rebate.getBackMoney()).setSource(rebate.getSource()).setLowName(name.getNickName());
            }
        }
        return new Page<User>(users,users.size());
    }

    /**
     * @param: user
     * @Description: 添加商户
     * @Author: LxH
     * @Date: 2020/5/26 15:29
     */
    @Override
    public ReturnData addMerchant(User user) {
        user.setActive((byte) 1).setType((byte) 2).setCreateTime(DateUtils.formatDate(new Date()));
        int i = userMapper.insertSelective(user);
        System.out.println(i);
        Rebate rebate = new Rebate();
        rebate.setUserId(user.getAppUserId()).setCreateTime(DateUtils.formatDate(new Date()));
        int i1 = merchantManagementMapper.insertUseGeneratedKeys(rebate);
        System.out.println(i1);
        if (i1!=1) {
            return returnData.setMessage("新增失败").setCode(Fail_CODE).setObject(false);
        }
        return returnData.setMessage("新增成功").setCode(SUCCESS_CODE).setObject(true);
    }

    /**
     * @param: user
     * @Description: 修改商家信息
     * @Author: LxH
     * @Date: 2020/5/26 15:49
     */
    @Override
    public ReturnData updateMerchant(User user) {
        user.setUpdateTime(DateUtils.formatDate(new Date()));
        if (userMapper.updateByPrimaryKeySelective(user)>0) {
            return returnData.setMessage("修改成功").setCode(SUCCESS_CODE).setObject(true);
        }
        return returnData.setMessage("修改失败").setCode(Fail_CODE).setObject(false);
    }

    /**
     * @param: userIds
     * @Description: 删除商户
     * @Author: LxH
     * @Date: 2020/5/26 15:59
     */
    @Override
    public ReturnData deleteMerchantById(Integer[] userIds) {
        for (Integer userId : userIds) {
            userMapper.deleteByPrimaryKey(userId);
            Example example = new Example(Rebate.class);
            example.createCriteria().andEqualTo(COLUMN_USER_ID,userId);
            if (merchantManagementMapper.deleteByExample(example)>0) {
                return returnData.setMessage("删除成功").setCode(SUCCESS_CODE).setObject(true);
            }
        }
        return returnData.setMessage("删除失败").setCode(Fail_CODE).setObject(false);
    }
}
