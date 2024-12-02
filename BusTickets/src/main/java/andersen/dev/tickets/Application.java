package andersen.dev.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import andersen.dev.tickets.service.TicketService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		TicketService service = context.getBean(TicketService.class);
		service.getAllTicketRepository().getViolations().forEach(System.out::println);
		System.out.println("Total = " + service.getAllTicketRepository().getAllTickets().size());
		System.out.println("Valid = " + service.getTickets().size());
		service.getTickets().stream().forEach(System.out::println);
	}
	
}
