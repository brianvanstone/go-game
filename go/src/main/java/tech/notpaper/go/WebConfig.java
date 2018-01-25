package tech.notpaper.go;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import tech.notpaper.go.controller.interceptors.ApiKeyHandler;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	ApiKeyHandler getApiKeyHandler() {
		return new ApiKeyHandler();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getApiKeyHandler()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
