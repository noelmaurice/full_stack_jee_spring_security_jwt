package com.ms2i.spring.security.jwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Configuration for the API documentation
 * 
 * @author NOEL MAURICE
 *
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@CrossOrigin(origins = "*", maxAge = 3600)
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {    
	
	/*
	 * Swagger url :
	 * 
	 * /v2/api-docs
	 * /v3/api-docs
	 * /swagger-ui.html
	 * /swagger-resources
	 * 
	 */
	
	/**
	 * 
	 * A builder which is intended to be the primary interface into the Springfox framework.
	 * Provides sensible defaults and convenience methods for configuration.
	 * 
	 * @return The api builder
	 */
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          // .apis(RequestHandlerSelectors.any())   
          .apis(RequestHandlerSelectors.basePackage("com.ms2i.spring.security.jwt.controllers"))
          .paths(PathSelectors.any())    
          // .paths(PathSelectors.regex("/api/auth.*"))
          .build();                                           
    }
    
    /**
     * Defines callback methods to customize the Java-based configuration forSpring MVC enabled 
     * via @EnableWebMvc. 
     * 
     * @return The web mvc configurer
     */
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// registry.addMapping("/**");
				registry.addMapping("/v3/api-docs").allowedOrigins("*");
			}
		};
	}
}