

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author Lxh
 * @date 2020/3/20 16:17
 */
@SpringBootApplication
@MapperScan(basePackages = "mapper")//用户扫描MyBatis 的Mapper 接口
@ComponentScan(value = {"service","mapper","controller","shiro"})
public class app {
    public static void main(String[] args) {
        SpringApplication.run(app.class, args);
    }
}
