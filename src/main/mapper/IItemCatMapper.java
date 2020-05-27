package mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pojo.AppItemCat;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import vo.Node;

import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/24 10:09
 */
public interface IItemCatMapper extends Mapper<AppItemCat>, MySqlMapper<AppItemCat>, BaseMapper<AppItemCat> {

    /**
     * @Description: tree节点信息查询
     * @Author: LxH
     * @Date: 2020/5/8 16:37
     */
    @Select("select app_item_category_id,category_name,pid from app_item_category")
    List<Node> findTreeNodes();
}
