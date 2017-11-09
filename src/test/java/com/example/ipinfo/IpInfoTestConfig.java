package com.example.ipinfo;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("test")
public class IpInfoTestConfig {
    
    @Bean
    public RestTemplate getRestTempate() {
        return Mockito.mock(RestTemplate.class);
    }
}
