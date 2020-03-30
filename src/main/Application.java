import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : cwj
 * @describe : 懒人家项目启动类
 * @date : 2020-3-17
 */
//存在IOC注入，启动器需要指定扫描哪个包下的类生成bean 注入
class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
