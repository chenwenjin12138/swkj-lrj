package controller.test;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.user.SysAdmin;
import service.IBusinessAdminService;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;


/**
 * @author fl
 * @Description:
 * @date 2020/5/6 0006下午 2:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysAdminServiceImplTest {
    @Autowired
    private IBusinessAdminService iBusinessAdminService;

    @Test
    public void getSysAdminPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(20);
     /*   SysAdmin SysAdmin = new SysAdmin();
        SysAdmin.setBusinessPhone("18388204538");
        SysAdmin.setBusinessName("张三");
        requestDTO.setData(SysAdmin);*/
       /* PageInfo pageInfo = iBusinessAdminService.getBusinessAdminPageByParam(requestDTO);
        for (Object or:pageInfo.getList()) {
            System.out.println("数据:"+or.toString());
        }*/
    }

    @Test
    public void updateSysAdmin() {
    }

    @Test
    public void addSysAdmin() {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setBusinessPhone("18388202475");
        sysAdmin.setBusinessName("开发第二家店");
        sysAdmin.setActive(0);
        sysAdmin.setAdminName("涪陵");
        sysAdmin.setAdminPassword("123456");
        sysAdmin.setBusinessDistributionRatio(new BigDecimal("0.06"));
        ReturnData<Boolean> returnData = iBusinessAdminService.addBusinessAdmin(sysAdmin);
        assertTrue(returnData.getData());
    }
}