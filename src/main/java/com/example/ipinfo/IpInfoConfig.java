package com.example.ipinfo;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IpInfoConfig {
    
    @Bean
    public RestTemplate getRestTempate() {
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        // for proxy
        httpClientBuilder.useSystemProperties();
        
        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        httpClientBuilder.setDefaultRequestConfig(requestBuilder.build());
        return new RestTemplate(
                new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build()));     
    }

}
