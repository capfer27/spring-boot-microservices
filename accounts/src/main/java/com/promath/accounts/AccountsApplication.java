package com.promath.accounts;

import com.promath.accounts.dto.AccountsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value = AccountsContactInfoDTO.class)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation for Accounts microservice",
                description = "Capfer Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Карлуш Перейра Фернандеш",
                        email = "capfer27@gmail.com",
                        url = "https://ulyanovsk.hh.ru/resume/ebc7d16cff03b5060d0039ed1f515351616748"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url="https://ulyanovsk.hh.ru/resume/ebc7d16cff03b5060d0039ed1f515351616748"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Capfer Accounts microservice REST API Documentation",
                url = "https://capfer-promath.com/swagger-ui.com.html"
        )
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
