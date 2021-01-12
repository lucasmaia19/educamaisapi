package com.example.educamaisapi.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.educamaisapi.interceptor.LogInterceptor;

@Configuration
@Component
public class InterceptorConf implements WebMvcConfigurer {

	public InterceptorConf() {
		super();
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor());
	}

}
