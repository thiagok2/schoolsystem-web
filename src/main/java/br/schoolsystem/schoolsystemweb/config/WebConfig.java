package br.schoolsystem.schoolsystemweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.allowCredentials(false)
		.maxAge(3600);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/admin/home").setViewName("adminhome");
		registry.addViewController("/accessDenied").setViewName("403");
	}
	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}
}
