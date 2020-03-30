package controller;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.SysAuthority;
import pojo.SysRole;
import pojo.SysUser;
import service.ISysManagementService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : cwj
 * @describe : 后台系统管理
 * @date : 2020-3-26
 */
@Controller
public class SysManagementController {

    @Resource
    private ISysManagementService sysManagementService;

    /**
     * 账号管理页面 并查询所有数据
     * @return
     */
    @RequestMapping(value = "/toAccountManangemet",method = {RequestMethod.GET,RequestMethod.POST})
    public String AccountManangemet(HttpServletRequest request){
        List<SysUser> accountList = sysManagementService.findAccountList();
        request.setAttribute("accountList",accountList);
        return "sysManagement/accountList";
    }

    /**
     * 系统角色页面
     * @return
     */
    @RequestMapping(value = "toRoleManagement",method = {RequestMethod.GET,RequestMethod.POST})
    public String RoleManagement(HttpServletRequest request){
        List<SysRole> roleList = sysManagementService.findRoleList();
        request.setAttribute("roleList",roleList);
        return "sysManagement/roleList";
    }

    /**
     * 系统权限页面
     * @return
     */
    @RequestMapping(value = "/toAuthorityManagement",method = {RequestMethod.GET,RequestMethod.POST})
    public String AuthorityManagement(HttpServletRequest request){
        List<SysAuthority> authorityList = sysManagementService.findAuthorityList();
        request.setAttribute("authorityList",authorityList);
        return "sysManagement/authorityList";
    }

    /**
     * 账号添加页面
     */
    @RequestMapping(value = "/toAddAccount",method = {RequestMethod.GET,RequestMethod.POST})
    public String toAddAccount(){
        return "sysManagement/addAccount";
    }

    /**
     * 检查 用户是否存在
     */
    @RequestMapping(value = "checkAccountIsExist",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> checkAccountIsExist(String adminName){
        Map<String, Object> resultMap = new HashMap<String,Object>();
        SysUser sysUser = sysManagementService.findAccountByAdminName(adminName);
        if(sysUser == null){
            resultMap.put("isExist", "0");
        }else {
            resultMap.put("isExist", "1");
        }
        return resultMap;
    }

    /**
     * 添加系统用户
     */
    @RequestMapping(value = "/addSysUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> addSysUser(SysUser sysUser){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer sysAdminId = sysManagementService.addSysUser(sysUser);
        if(sysAdminId !=null || sysAdminId!=0){
            resultMap.put("errorMsg","success");
        }else {
            resultMap.put("errorMsg", "添加用户失败");
        }
        return resultMap;
    }

    /**
     *  查询系统角色(所有)
     */
    @RequestMapping(value = "selectRoleList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> findSysRoleList(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("errorMsg", "success");
        List<SysRole> roleList = sysManagementService.findRoleList();
        resultMap.put("resultDate", roleList);
        return resultMap;
    }

}
