package service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import dto.RequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import pojo.user.AppUser;

import java.util.List;
import java.util.Map;

/**
 * @author : fl
 * @describe : APP用户管理
 * @date : 2020-4-28
 */
public interface IAppUserService {

    /**
     * 根据条件查询用户信息
     * @param RequestDTO 查询条件
     * @return
     */
    PageInfo<AppUser> getAppUserPageByParam(RequestDTO RequestDTO);

    /**
     * 修改App用户对象
     * @param appUser
     * @return
     */
    boolean updateAppUser(AppUser appUser);
}
