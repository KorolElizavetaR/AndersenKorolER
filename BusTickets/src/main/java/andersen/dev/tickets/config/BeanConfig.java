package andersen.dev.tickets.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("andersen.dev.tickets")
@PropertySource("classpath:application.properties")
public class BeanConfig {

}
