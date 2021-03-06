package controller.test;

import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.user.AppUser;
import service.IAppUserService;

import javax.validation.constraints.Size;

import static org.junit.Assert.*;

/**
 * @author fl
 * @Description:
 * @date 2020/4/30 0030下午 2:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUserServiceImplTest {
    @Autowired
    private IAppUserService iAppUserService;

    @Test
    public void getAppUserPageByParam() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(2);
       /* PageInfo pageInfo = iAppUserService.getAppUserPageByParam(requestDTO);
        assertEquals(pageInfo.getSize(),2);*/
    }

    @Test
    public void updateAppUser() {
        AppUser appUser = new AppUser();
        appUser.setAppUserId(1);
        appUser.setCreateTime("2020-04-21 10:18:37");
        appUser.setActive(1);
        boolean r = iAppUserService.updateAppUser(appUser).getData();
        assertTrue(r);
    }
}