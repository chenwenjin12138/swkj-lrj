package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fl
 * @descrip: swagger配置类
 * @date 2020/5/18 0018下午 3:12
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig  {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("懒人家后台管理接口")
                .apiInfo(apiInfo())
                .host("localhost:8080")
                .select()
                .apis(RequestHandlerSelectors.basePackage("controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("懒人家后台管理接口")
                .description("懒人家后台管理接口")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}
