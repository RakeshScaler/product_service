package com.demo.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public RestTemplate craeteRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}
}
