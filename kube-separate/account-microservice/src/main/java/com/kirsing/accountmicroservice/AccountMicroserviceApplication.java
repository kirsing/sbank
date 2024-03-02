package com.kirsing.accountmicroservice;

import com.kirsing.accountmicroservice.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
//@ComponentScans({@ComponentScan("com.kirsing.accountmicroservice.controller")})
//@EnableJpaRepositories("com.kirsing.accountmicroservice.repository")
//@EntityScan("com.kirsing.accountmicroservice.entity")  // for cases when there are different packages
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "SBANK Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Kirill Yudaev",
                        email = "kirsing98@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "SBANK Accounts microservice REST API Documentation"
        )
)
public class AccountMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMicroserviceApplication.class, args);
    }

}
