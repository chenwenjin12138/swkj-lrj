package controller.test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import lombok.AllArgsConstructor;
import mapper.ValueAddServicesMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;
import pojo.ValueAddedServices;
import service.IValueAddServicesService;
import util.DateUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/13 0013下午 3:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValueAddServicesServiceImplTest {

    @Autowired
    private IValueAddServicesService valueAddServicesService;

    @Test
    public void getListByParam() {
        RequestDTO requestDTO = new RequestDTO();
        List list = valueAddServicesService.getListByParam(requestDTO);
        for (Object or : list) {
            System.out.println("数据:" + or.toString());
        }
    }

    @Test
    public void add() {
        ValueAddedServices valueAddedServices = new ValueAddedServices();
        valueAddedServices.setCreateAdminId("1");
        valueAddedServices.setServicePrice(new BigDecimal("2.90"));
        valueAddedServices.setServiceDescription("服务费2");
        valueAddedServices.setCreateTime(DateUtils.getNowDateTime());
//        assertTrue(valueAddServicesService.add(valueAddedServices).getData());
    }

    @Test
    public void update() {
        ValueAddedServices valueAddedServices = new ValueAddedServices();
        valueAddedServices.setValueAddedServicesId(1);
        valueAddedServices.setUpdateAdminId("1");
        valueAddedServices.setServicePrice(new BigDecimal("2.92"));
        valueAddedServices.setServiceDescription("服务费2");
//        assertTrue(valueAddServicesService.update(valueAddedServices).getData());
    }

    @Test
    public void delete() {
//        assertTrue(valueAddServicesService.delete(5).getData());
    }
}