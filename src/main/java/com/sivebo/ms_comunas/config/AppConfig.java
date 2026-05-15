package com.sivebo.ms_comunas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class AppConfig {

        @Value("${ms.regiones.url}")
        private String regionesBaseUrl;
        
        @Bean
        public WebClient regionesWebClient(WebClient.Builder builder) {
                return builder
                        .baseUrl(regionesBaseUrl)
                        .build();
        }
}
