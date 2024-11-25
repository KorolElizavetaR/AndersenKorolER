package andersen.dev.tickets;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.BeanConfig;
import andersen.dev.tickets.service.UserService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		UserService service = context.getBean(UserService.class);
	}
	
}
