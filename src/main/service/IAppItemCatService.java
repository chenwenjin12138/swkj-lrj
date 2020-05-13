package service;

import dto.ReturnData;
import pojo.AppItemCat;
import pojo.Balance;
import vo.Node;

import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/8 16:08
 */
public interface IAppItemCatService {

    /**
     * @Description: tree节点信息查询
     * @Author: LxH
     * @Date: 2020/5/8 16:35
     */
    List<Node> findTreeNodes();

    /**
     * @Description: 获取全部商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:21
     */
    ReturnData<List<AppItemCat>> findAllItemCats();

    /**
     * @Description: 添加商品种类信息
     * @Author: LxH
     * @Date: 2020/5/8 17:53
     */
    ReturnData<Boolean> addAppItemCat(AppItemCat appItemCat);
}
