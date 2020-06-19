package controller.test;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import dto.ReturnData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.user.AppStaff;
import pojo.user.AppUser;
import service.IAppStaffService;
import service.IAppUserService;

import static org.junit.Assert.*;

/**
 * @author fl
 * @Description:
 * @date 2020/4/30 0030下午 2:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppStaffServiceImplTest {
    @Autowired
    private IAppStaffService iAppStaffService;

    @Test
    public void getAppUserPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(2);
        AppStaff appStaff = new AppStaff();
        appStaff.setTelephone("518435");
        appStaff.setRealName("李官留");
        requestDTO.setData(appStaff);
        iAppStaffService.getAppUserPageByParam(requestDTO);
    }
    @Test
    public void addAppStaff() {
        AppStaff appStaff = new AppStaff();
        appStaff.setActive(0);
        appStaff.setAddress("五华区设计院");
        appStaff.setRealName("叔本华");
        appStaff.setRegisterTime("2020-05-06 10:20:20");
        appStaff.setType(1);
        ReturnData<Boolean>returnData = iAppStaffService.addAppStaff(appStaff);
        assertTrue(returnData.getData());

    }

    @Test
    public void updateAppStaff() {
        AppStaff appStaff = new AppStaff();
        appStaff.setAppStaffId(1);
        appStaff.setActive(1);
        appStaff.setAddress("五华区设计院");
        appStaff.setRealName("叔本华");
        appStaff.setRegisterTime("2020-05-06 10:20:20");
        appStaff.setType(1);
        appStaff.setIsDeleted(1);
        ReturnData<Boolean>returnData = iAppStaffService.updateAppStaff(appStaff);
        assertTrue(returnData.getData());
    }
}