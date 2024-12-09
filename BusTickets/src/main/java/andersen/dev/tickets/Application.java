package andersen.dev.tickets;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.BeanConfig;
import andersen.dev.tickets.service.UserService;

public class Application {

	public static void main(String[] args) {
<<<<<<< HEAD
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		UserService service = context.getBean(UserService.class);
=======
		ApplicationContext context = SpringApplication.run(Application.class, args);
		TicketService service = context.getBean(TicketService.class);
		service.getAllTicketRepository().getViolations().forEach(System.out::println);
		System.out.println("Total = " + service.getAllTicketRepository().getAllTickets().size());
		System.out.println("Valid = " + service.getTickets().size());
		service.getTickets().stream().forEach(System.out::println);
>>>>>>> master
	}
	
}
