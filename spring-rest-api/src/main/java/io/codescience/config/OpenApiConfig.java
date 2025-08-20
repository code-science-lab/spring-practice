package io.codescience.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring REST API - 用户管理服务")
                        .description("这是一个基于Spring Boot的REST API服务，提供用户管理功能。包括用户的增删改查操作。")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("CodeScience")
                                .email("contact@codescience.io"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("开发环境服务器"),
                        new Server()
                                .url("https://api.codescience.io")
                                .description("生产环境服务器")));
    }
}
