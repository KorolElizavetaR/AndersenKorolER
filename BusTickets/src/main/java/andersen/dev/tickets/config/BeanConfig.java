package andersen.dev.tickets.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("andersen.dev.tickets")
@PropertySource("classpath:application.properties")
public class BeanConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
