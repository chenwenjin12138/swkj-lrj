package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dto.ReturnData;
import mapper.IItemCatMapper;
import org.springframework.stereotype.Service;
import pojo.AppItemCat;
import service.IAppItemCatService;
import vo.Node;

import javax.annotation.Resource;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/8 16:08
 */
@Service
public class AppItemCatServiceImpl implements IAppItemCatService {

    @Resource
    private IItemCatMapper itemCatMapper;

    private ReturnData returnData = new ReturnData();

    /**
     * @Description: tree节点信息查询
     * @Author: LxH
     * @Date: 2020/5/8 16:35
     */
    @Override
    public List<Node> findTreeNodes() {
        return itemCatMapper.findTreeNodes();
    }

    /**
     * @Description: 获取全部商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:21
     */
    @Override
    public ReturnData findAllItemCats() {
        List<AppItemCat> appItemCats = itemCatMapper.selectAll();
        return returnData.setCode(SUCCESS_CODE).setMessage("获取成功").setObject(appItemCats);
    }

    /**
     * @param: appItemCat
     * @Description: 添加商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:53
     * @return
     */
    @Override
    public ReturnData addAppItemCat(AppItemCat appItemCat) {
        if (itemCatMapper.insertSelective(appItemCat) > 0) {
            return returnData.setCode(SUCCESS_CODE).setMessage("添加商品种类信息成功!").setObject(true);
        }
        return returnData.setCode(Fail_CODE).setMessage("添加商品种类信息失败!").setObject(false);
    }

    @Override
    public List<AppItemCat> findListByParam(List<String> ids) {
        QueryWrapper<AppItemCat> queryWrapper = new QueryWrapper();
        queryWrapper.in(AppItemCat.ID_COLUMN,ids);
        return itemCatMapper.selectList(queryWrapper);
    }
}
