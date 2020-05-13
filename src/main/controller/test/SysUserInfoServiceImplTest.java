package controller.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.ISysUserInfoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/12 0012下午 1:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserInfoServiceImplTest {

    @Autowired
    ISysUserInfoService iSysUserInfoService;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getUserInfoByLoginInfo() {
    }

    @Test
    public void getSysAuthoritysByAdmin() {
    }

    @Test
    public void getSysRoleByAdminId() {
        System.out.println(iSysUserInfoService.getSysRoleByAdminId(1));
        System.out.println("所有角色："+iSysUserInfoService.getSysRole().size());
        System.out.println("所有菜单："+iSysUserInfoService.getAllSysAuthority().size());
    }

    @Test
    public void getAuthorityByRoleId() {
    }
}