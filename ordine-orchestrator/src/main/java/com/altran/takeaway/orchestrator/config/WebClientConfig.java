package com.altran.takeaway.orchestrator.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("cucine")
    public WebClient cucineClient(@Value("${service.endpoints.cucine}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }

    @Bean
    @Qualifier("consegne")
    public WebClient inventoryClient(@Value("${service.endpoints.consegne}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }

}
