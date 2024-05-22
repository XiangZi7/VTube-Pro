package com.vtube.comfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger文档，只有在测试环境才会使用
 * @author yxcr
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi createRestApi() {
        return GroupedOpenApi.builder()
                .group("专业版")
                .packagesToScan("com.yxcr")
                .build();
    }


    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
                .info(new Info().title("yxcr接口文档")
                        .description("yxcr接口文档，openapi3.0 接口，用于前端对接")
                        .version("v0.0.1")
                        .license(new License().name("使用请遵守商用授权协议").url("https://www.yxcr.com")));
    }

}