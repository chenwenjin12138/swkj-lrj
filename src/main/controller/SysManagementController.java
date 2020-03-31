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
    @RequestMapping(value = "/toRoleManagement",method = {RequestMethod.GET,RequestMethod.POST})
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
     *  查询系统角色(所有)
     */
    @RequestMapping(value = "/selectRoleList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> findSysRoleList(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("errorMsg", "success");
        List<SysRole> roleList = sysManagementService.findRoleList();
        resultMap.put("resultDate", roleList);
        return resultMap;
    }

    /**
     * 账号(系统用户)添加页面
     */
    @RequestMapping(value = "/toAddAccount",method = {RequestMethod.GET,RequestMethod.POST})
    public String toAddAccount(){
        return "sysManagement/addAccount";
    }

    /**
     * 检查 用户是否存在
     */
    @RequestMapping(value = "/checkAccountIsExist",method = {RequestMethod.GET,RequestMethod.POST})
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
        SysUser sysUserNew = sysManagementService.addSysUser(sysUser);
        //调用了插入数据传回自增的Id  被注入到插入对象中了
        Integer sysAdminId = sysUserNew.getSysAdminId();
        if(sysAdminId !=null || sysAdminId!=0){
            resultMap.put("errorMsg","success");
        }else {
            resultMap.put("errorMsg", "添加用户失败");
        }
        return resultMap;
    }

    /**
     * 系统用户编辑页面
     */
    @RequestMapping(value = "/toEditSysUser",method = {RequestMethod.GET,RequestMethod.POST})
    public String toEditSysUser(String sysAdminId,HttpServletRequest request){
        SysUser sysUser = sysManagementService.findSysUserById(sysAdminId);
        request.setAttribute("sysUser",sysUser);
        return "sysManagement/editAccount";
    }
    /**
     * 更新系统用户信息
     */
    @RequestMapping(value = "/editSysUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> editSysUser(SysUser sysUser){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //更新数据后返回 受影响的行数
        Integer updateNum = sysManagementService.updateSysUser(sysUser);
        if(updateNum == 1){
            resultMap.put("errorMsg","success");
        }else {
            resultMap.put("errorMsg", "更新用户失败");
        }
        return resultMap;
    }
}
