package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import mapper.AppPushMapper;
import org.springframework.stereotype.Service;
import pojo.AppPush;
import service.IAppPushService;
import util.DateUtils;

import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;
import static pojo.AppPush.CREATE_TIME_COLUMN;

/**
 * @author fl
 * @descrip: 消息推送
 * @date 2020/5/13 0013上午 10:27
 */
@Service
public class AppPushServiceImpl implements IAppPushService {
    private AppPushMapper appPushMapper;

    public AppPushServiceImpl(AppPushMapper appPushMapper) {
        this.appPushMapper = appPushMapper;
    }

    @Override
    public PageInfo<AppPush> getMessagePageByParam(RequestDTO requestDTO) {
        QueryWrapper<AppPush> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc(CREATE_TIME_COLUMN);
        PageHelper.startPage(requestDTO.getPage(),requestDTO.getSize());
        List<AppPush> list = appPushMapper.selectList(queryWrapper);
        return new PageInfo<AppPush>(list);
    }

    @Override
    public ReturnData<Boolean> addPush(AppPush push) {
       /* try {
            PushUtils.SendPushAll(push.getTitle(), push.getAlert());
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(SUCCESS_CODE,"推送失败",false);
        }*/
        push.setCreateTime(DateUtils.getNowDateTime());
        try {
            if (appPushMapper.insert(push) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }

    @Override
    public ReturnData<Boolean> deletePush(AppPush push){
       if(push ==null || StringUtils.isEmpty(push.getAppPushId().toString())){
           return new ReturnData(Fail_CODE,"操作失败,请选择要删除的数据",false );
       }
        try {
            if (appPushMapper.deleteById(push.getAppPushId()) > 0) {
                return new ReturnData(SUCCESS_CODE,"操作成功",true );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnData(Fail_CODE,"操作失败",false );
    }
}
