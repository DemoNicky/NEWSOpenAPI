package com.dobudobu.file_service.Config.OpenApi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI articleServiceApi(){
        return new OpenAPI()
                .info(new Info().title("File Service API")
                        .description("This is service for File service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("you can refer to the File service documentation")
                        .url("www.belom-ada.com"));
    }

}
