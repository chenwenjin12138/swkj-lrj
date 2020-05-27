package controller.test;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.IAppItemCatService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/27 0027下午 2:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppItemCatServiceImplTest {
    @Autowired
    private IAppItemCatService appItemCatService;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findListByParam() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("20");
        System.out.println(appItemCatService.findListByParam(list));
    }
}