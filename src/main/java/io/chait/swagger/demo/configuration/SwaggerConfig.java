package io.chait.swagger.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;


import java.util.Arrays;



import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@Slf4j
public class SwaggerConfig {

    @Value("${keycloak.auth-server-url}")
    private String AUTH_SERVER;

    @Value("${keycloak.credentials.secret}")
    private String CLIENT_SECRET;

    @Value("${keycloak.resource}")
    private String CLIENT_ID;

    @Value("${keycloak.realm}")
    private String REALM;

    private static final String OAUTH_NAME = "spring_oauth";
    private static final String ALLOWED_PATHS = "src/main/java/io/chait/swagger/demo/.*";
    private static final String GROUP_NAME = "swagger-demo";
    private static final String TITLE = "API Documentation for swagger-demo Application";
    private static final String DESCRIPTION = "Description here";
    private static final String VERSION = "1.0";

    @Bean
    public Docket taskApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME)
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo())
                .select()
                .paths(regex(ALLOWED_PATHS))
                .build()
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new
                ApiInfoBuilder().title(TITLE).description(DESCRIPTION).version(VERSION).build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .realm(REALM)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .appName(GROUP_NAME)
                .scopeSeparator(" ")
                .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType =
                new AuthorizationCodeGrantBuilder()
                        .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/realms/" + REALM + "/protocol/openid-connect/token", GROUP_NAME))
                        .tokenRequestEndpoint(
                                new TokenRequestEndpoint(AUTH_SERVER + "/realms/" + REALM + "/protocol/openid-connect/auth", CLIENT_ID, CLIENT_SECRET))
                        .build();

        SecurityScheme oauth =
                new OAuthBuilder()
                        .name(OAUTH_NAME)
                        .grantTypes(Arrays.asList(grantType))
                        .scopes(Arrays.asList(scopes()))
                        .build();
        return oauth;
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("user", "for CRUD operations"),
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")
        };
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference(OAUTH_NAME, scopes())))
                .forPaths(PathSelectors.regex(ALLOWED_PATHS))
                .build();
    }

}
