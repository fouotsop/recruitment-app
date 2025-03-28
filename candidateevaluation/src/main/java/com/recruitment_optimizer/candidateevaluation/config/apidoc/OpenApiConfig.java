package com.recruitment_optimizer.candidateevaluation.config.apidoc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
   
    private final String devUrl;
    private final String serverContext;

    public OpenApiConfig(
        @Value("${app.prod.url}") String devUrl,
        @Value("${server.servlet.context-path}") String serverContext) {

        this.devUrl = devUrl;
        this.serverContext = serverContext;
    }

    @Bean
    public OpenAPI openApi() {

        final String devFullUrl = devUrl + serverContext;

        Server devServer = new Server();
        devServer.setUrl(devFullUrl);
        devServer.setDescription("Server url for development environment");

        Contact contact = new Contact();
        contact.setEmail("patrickfosso49@gmail.cm");
        contact.setName("Patrick Fouotsop Fosso");
        contact.setUrl("https://fouotsop.github.io/portfolio");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");
        Info info = new Info()
                .title("RECRUITMENT SERVICE API")
                .description("API for endpoints performing operations on the recruitment service")
                .version("1.0.0")
                .contact(contact)
                .license(mitLicense);

        return new OpenAPI().info(info)
                .addServersItem(devServer)
                .schemaRequirement("Bearer", securityScheme())
                .security(securityRequirements())
                ;
    }

    private SecurityScheme securityScheme() {

            SecurityScheme scheme = new SecurityScheme();
            scheme.setType(SecurityScheme.Type.HTTP);
            scheme.setScheme("bearer");
            scheme.setBearerFormat("JWT");
            scheme.setIn(SecurityScheme.In.HEADER);
            return scheme;
        }
    
    
    private List<SecurityRequirement> securityRequirements() {
        
        List<SecurityRequirement> securityRequirements = new ArrayList<>();
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("Bearer");

        securityRequirements.add(securityRequirement);

        return securityRequirements;
    }
   

}
