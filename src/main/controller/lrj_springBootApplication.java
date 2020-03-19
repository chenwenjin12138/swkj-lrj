package controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : cwj
 * @describe : 懒人家项目启动类
 * @date : 2020-3-17
 */
@SpringBootApplication
@MapperScan("mapper")//用户扫描MyBatis 的Mapper 接口
@ComponentScan(value = {"service","mapper","controller","shiro"})  //存在IOC注入，启动器需要指定扫描哪个包下的类生成bean 注入
public class lrj_springBootApplication {
    public static void main(String[] args){
        SpringApplication.run(lrj_springBootApplication.class, args);
    }
}
