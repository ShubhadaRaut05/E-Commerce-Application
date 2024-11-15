package com.shubhada.productservice.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate(){
        //instance of RestTemplate, if given list of urls will load balanced between them
        return new RestTemplate();
    }

}
