package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/23 9:41
 */
public class BaseControllerAdaptor<T> extends AbstractBaseController<T> {


    @Override
    public String initList() {
        return null;
    }

    @Override
    public String initAdd() {
        return null;
    }

    @Override
    public String initEdit() {
        return null;
    }

    @Override
    public String initDel() {
        return null;
    }

    @Override
    public List<?> list(T t, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Result add(T t, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Result edit(T t, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Result del(Integer id, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
