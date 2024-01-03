package com.kirsing.loansmicroservice;

import com.kirsing.loansmicroservice.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "SBANK Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Kirill",
                        email = "kirsing98@gmail.com"),
                license = @License(
                        name = "Apache 2.0")
        ),
        externalDocs = @ExternalDocumentation(
                description = "SBANK Loans microservice REST API Documentation")
)
public class LoansMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansMicroserviceApplication.class, args);
    }

}
