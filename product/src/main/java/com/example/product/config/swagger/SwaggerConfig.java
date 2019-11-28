//package com.example.product.config.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * 配置swagger 2.0
// *
// * @author ONEC
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().
//                apis(RequestHandlerSelectors.basePackage("com.example.product")).
//                paths(PathSelectors.any()).build();
//    }
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("产品服务API：").description("产品服务接口API").
//                termsOfServiceUrl("").version("1.0").build();
//    }
//}
