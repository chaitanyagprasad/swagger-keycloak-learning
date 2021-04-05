//package io.chait.swagger.demo.configuration;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.AuthorizationScopeBuilder;
//import springfox.documentation.builders.LoginEndpointBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Configuration@Slf4j
//public class SwaggerConfig2 {
//
//    @Value("${keycloak.auth-server-url}")
//    private String authUrl;
//
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Value("${keycloak.resource}")
//    private String clientId;
//
//    @Value("${keycloak.credentials.secret}")
//    private String clientSecret;
//
//    @Bean
//    public SecurityConfiguration securityConfiguration() {
//
//        Map<String, Object> additionalQueryStringParams=new HashMap<>();
//        additionalQueryStringParams.put("nonce","123456");
//
//        return SecurityConfigurationBuilder.builder()
//                .clientId(clientId)
//                .realm("Master")
//                .clientSecret(clientSecret)
//                .appName("swagger-ui")
//                .useBasicAuthenticationWithAccessCodeGrant(false)
//                .scopeSeparator(" ")
//                .enableCsrfSupport(false)
//                .additionalQueryStringParams(additionalQueryStringParams)
//                .build();
//    }
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(buildSecurityScheme())
//                .securityContexts(buildSecurityContext());
//    }
//
//    private List<SecurityContext> buildSecurityContext() {
//        List<SecurityReference> securityReferences = new ArrayList<>();
//
//        securityReferences.add(SecurityReference.builder().reference("oauth2").scopes(scopes().toArray(new AuthorizationScope[]{})).build());
//
//        SecurityContext context = SecurityContext.builder().forPaths(PathSelectors.any()).securityReferences(securityReferences).build();
//
//        List<SecurityContext> ret = new ArrayList<>();
//        ret.add(context);
//        return ret;
//    }
//
//    private List<SecurityScheme> buildSecurityScheme() {
//        List<SecurityScheme> lst = new ArrayList<>();
//
//        StringBuilder sb = new StringBuilder(authUrl)
//                .append("/realms/")
//                .append(realm)
//                .append("/protocol/openid-connect/auth");
//
//        log.info("Token Url is {} ", sb.toString());
//
//
//        LoginEndpoint login = new LoginEndpointBuilder().url(sb.toString()).build();
//
//        List<GrantType> gTypes = new ArrayList<>();
//        gTypes.add(new ImplicitGrant(login, "access_token"));
//
//        lst.add(new OAuth("oauth2", scopes(), gTypes));
//        return lst;
//    }
//
//    private List<AuthorizationScope> scopes() {
//        List<AuthorizationScope> scopes = new ArrayList<>();
//        for (String scopeItem : new String[]{"openid=openid", "profile=profile"}) {
//            String scope[] = scopeItem.split("=");
//            if (scope.length == 2) {
//                scopes.add(new AuthorizationScopeBuilder().scope(scope[0]).description(scope[1]).build());
//            } else {
//                log.warn("Scope '{}' is not valid (format is scope=description)", scopeItem);
//            }
//        }
//
//        return scopes;
//    }
//}
