package net.futureorigin.architecture.sample.cola.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4jConfiguration
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "apiDoc")
    public Docket apiDoc() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("COLA Sample API Docs")
                                .version("1.0")
                                .build()
                )
                //分组名称
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("net.futureorigin.architecture.sample.cola"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}