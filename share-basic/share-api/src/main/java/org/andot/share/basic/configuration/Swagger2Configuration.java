package org.andot.share.basic.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 2 配置类
 *
 * @author Andot
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.andot.share.basic"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()))
                .globalOperationParameters(setHeaderToken());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("lucas", "https://andotorg.github.io/share-platform", "xieyan669@gmail.com");
        return new ApiInfoBuilder()
                .title("Share-Platform RESTful APIs")
                .description("Share")
                .termsOfServiceUrl("https://andotorg.gitbook.io/share")
                .contact(contact)
                .version("1.0.0")
                .license("MIT")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("X-Token", "X-Token", "header");
    }

    /**
     * JWT token
     * @return
     */
    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("X-Token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }
}
