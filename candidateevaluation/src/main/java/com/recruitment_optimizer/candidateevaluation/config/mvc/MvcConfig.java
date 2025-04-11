package com.recruitment_optimizer.candidateevaluation.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    static MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }


        
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("*");
    }

    @Bean
    RestTemplate restTemplate() {
		return new RestTemplate();
	}	

    @Override
            public void configureContentNegotiation(@NonNull ContentNegotiationConfigurer configurer) {
            configurer.defaultContentType(MediaType.APPLICATION_JSON);
        }
}
