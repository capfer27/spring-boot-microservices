package com.capfer.cards;

import com.capfer.cards.dto.CardsContactInfoDTO;
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
@EnableJpaAuditing // Enable JPA Auditing
@EnableConfigurationProperties(CardsContactInfoDTO.class)
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice REST API Documentation",
                description = "CapferBank Cards microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Карлуш Перейра Фернандеш",
                        email = "capfer27@gmail.com",
                        url = "https://ulyanovsk.hh.ru/resume/ebc7d16cff03b5060d0039ed1f515351616748\""
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://ulyanovsk.hh.ru/resume/ebc7d16cff03b5060d0039ed1f515351616748\""
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "CapferBank Cards microservice REST API Documentation",
                url = "https://capfer-promath.com/swagger-ui.com.html"
        )
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
