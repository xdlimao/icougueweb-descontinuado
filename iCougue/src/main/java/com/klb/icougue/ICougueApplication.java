package com.klb.icougue;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

//Editando

@SpringBootApplication
@EnableConfigurationProperties
public class ICougueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ICougueApplication.class, args);
    }
    
    @Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}
}

