package com.sudip.fusecanteen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${security.clientId}")
    String clientId;
    @Value("${security.clientSecret}")
    String clientSecret;

    @Value("${auth.url}")
    String oAuthServerUri;

    @Bean
    public Docket api() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        java.util.List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sudip.fusecanteen.resources"))
                .paths(PathSelectors.any())
                .build()/*.securitySchemes(Collections.singletonList(oauth()))*/;
    }

}
