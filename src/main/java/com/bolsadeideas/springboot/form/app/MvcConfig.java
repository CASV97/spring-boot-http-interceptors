package com.bolsadeideas.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bolsadeideas.springboot.form.app.interceptors.ElapsedTimeInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Autowired
	@Qualifier("elapsedTimeInterceptor")
	private ElapsedTimeInterceptor elapsedTimeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(elapsedTimeInterceptor);
	}

}
