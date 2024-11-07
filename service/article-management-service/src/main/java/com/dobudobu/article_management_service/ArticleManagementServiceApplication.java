package com.dobudobu.article_management_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Article-Management-Service Rest Api Documentation",
				description = "Article microservice RestApi Documentation",
				version = "v1",
				contact = @Contact(
						name = "ucok",
						email = "ucok@gmail.com",
						url = "https://www.youtube.com/watch?v=8PcxiY4YFrE"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
public class ArticleManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleManagementServiceApplication.class, args);
	}

}
