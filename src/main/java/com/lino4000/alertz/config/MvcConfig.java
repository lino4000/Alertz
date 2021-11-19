package com.lino4000.alertz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	if (!registry.hasMappingForPattern("/img/**")) {
    		registry.addResourceHandler("/img/**").addResourceLocations(
    				"classpath:/static/img/");
    	}
    	if (!registry.hasMappingForPattern("/theme/**")) {
    		registry.addResourceHandler("/theme/**").addResourceLocations(
    				"classpath:/static/theme/");
    	}
    	if (!registry.hasMappingForPattern("/js/**")) {
    		registry.addResourceHandler("/js/**").addResourceLocations(
    				"classpath:/static/js/");
    	}
    }
	
}