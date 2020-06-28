package controller;


        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.ComponentScan;
        import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author : cwj
 * 74
 * +-4
 * @describe : 懒人家项目启动类1
 * @date : 2020-3-17
 */
@SpringBootApplication
@MapperScan(basePackages = "mapper")//用户扫描MyBatis 的Mapper 接口
@ComponentScan(value = {"service","mapper","controller","shiro","config"})  //存在IOC注入，启动器需要指定扫描哪个包下的类生成bean 注入
class LrjApplication {
    public static void main(String[] args){
        SpringApplication.run(LrjApplication.class, args);
    }
}
