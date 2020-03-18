package controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : cwj
 * @describe : 懒人家项目启动类
 * @date : 2020-3-17
 */
@SpringBootApplication
@MapperScan("mapper")//用户扫描MyBatis 的Mapper 接口
public class lrj_springBootApplication {
    public static void main(String[] args){
        SpringApplication.run(lrj_springBootApplication.class, args);
    }
}
