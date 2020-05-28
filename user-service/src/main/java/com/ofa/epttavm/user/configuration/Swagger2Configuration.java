package com.ofa.epttavm.user.configuration;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration implements WebMvcConfigurer {
    private static final String PATH = "/user";
    private static final String BASE_PACKAGE_NAME = "com.ofa.epttavm.user.controller";


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_NAME))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        final String apiDocs = "/v2/api-docs";
        final String configUi = "/swagger-resources/configuration/ui";
        final String configSecurity = "/swagger-resources/configuration/security";
        final String resources = "/swagger-resources";

        registry.addRedirectViewController(PATH + apiDocs, apiDocs).setKeepQueryParams(true);
        registry.addRedirectViewController(PATH + resources, resources);
        registry.addRedirectViewController(PATH + configUi, configUi);
        registry.addRedirectViewController(PATH + configSecurity, configSecurity);
        registry.addRedirectViewController(PATH, "/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH + "/**").addResourceLocations("classpath:/META-INF/resources/");
    }
}
