package com.altran.takeaway.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class OrchestratorApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(OrchestratorApplication.class, args);
    }
	
	@Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }

}
