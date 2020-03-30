package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/23 9:33
 */
public abstract class AbstractBaseController<T> {
    /**
     * <b>initList</b>：(此方法为跳转查询列表页面.)<br>
     * <b>url</b>：(basePath../xxx/init-list)<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public abstract String initList();

    /**
     * <b>initAdd</b>：(此方法为跳转新增页面。)<br>
     * <b>url</b>：(basePath../xxx/init-add)<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public abstract String initAdd();

    /**
     * <b>initEdit</b>：(此方法为跳转编辑页面.)<br>
     * <b>url</b>：(basePath../xxx/init-edit)<br>
     * @param<br>
     * @param<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public abstract String initEdit();


    @Deprecated
    public abstract String initDel();

    /**
     * <b>list</b>：(查询数据信息,根据实体所对应条件.)<br>
     * @param T
     *            t 泛型 对应具体实体对象.<br>
     * @return List<T><br>
     * @Exception<br>
     * @author SAM QZL
     */
    public abstract List<?> list(T t, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * <b>add</b>：(持久化对应实体对象,即新增一条数据.)<br>
     * <b>TODO</b>：(需通过ajaxPOST请求该接口)<br>
     * <b>url</b>：(basePath..)<br>
     * @param T
     *            t 泛型 对应具体实体对象.<br>
     * @return Result<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public abstract Result add(T t, HttpServletRequest request, HttpServletResponse response);

    /**
     * <b>edit</b>：(更新对应的实体对象)<br>
     * @param T
     *            t 泛型参数<br>
     * @return Result<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public abstract Result edit(T t, HttpServletRequest request, HttpServletResponse response);

    /**
     * <b>del</b>：(根据ID,删除一条与之匹配的数据库记录.)<br>
     * @param Integer
     *            id <br>
     * @return Result<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public abstract Result del(Integer id, HttpServletRequest request, HttpServletResponse response);

}
