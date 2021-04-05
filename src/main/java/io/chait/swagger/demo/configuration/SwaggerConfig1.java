//package io.chait.swagger.demo.configuration;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//@Slf4j@EnableSwagger2
//public class SwaggerConfig1 {
//
//    private static final String API_DESCRIPTION = "Swagger API";
//
//    @Value("${server.version}")
//    private String serverVersion;
//
//    @Value("${spring.application.name}")
//    private String applicationName;
//
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Value("${swagger.auth.token-url:}")
//    private String authTokenUrl;
//
//    @Value("${swagger.auth.client-id:}")
//    private String authClientId;
//
//    @Value("${keycloak.credentials.secret}")
//    private String clientSecret;
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .paths(PathSelectors.any())
//                .build()
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(securitySchema()));
//    }
//
//    @Bean
//    public SecurityConfiguration securityConfiguration() {
//        return new SecurityConfiguration(authClientId, clientSecret , realm, applicationName, " ", Collections.emptyMap(), false, false);
//    }
//
//    private OAuth securitySchema() {
//        List<GrantType> grantTypes = Arrays.asList(new ResourceOwnerPasswordCredentialsGrant(authTokenUrl));
//        return new OAuth("oauth2", Collections.EMPTY_LIST, grantTypes);
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Arrays.asList(new SecurityReference("oauth2", new AuthorizationScope[0])))
//                .forPaths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title(applicationName)
//                .description(API_DESCRIPTION)
//                .version(serverVersion)
//                .build();
//    }
//
//}
