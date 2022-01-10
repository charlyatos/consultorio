package net.atos.crojo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperE {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
