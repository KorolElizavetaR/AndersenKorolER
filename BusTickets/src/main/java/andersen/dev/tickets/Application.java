package andersen.dev.tickets;

import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.BeanConfig;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.service.TicketService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		//UserService userService = context.getBean(UserService.class);
		TicketService ticketService = context.getBean(TicketService.class);
		
		ticketService.insertTicket(new Ticket("AAA", TicketType.DAY, LocalDate.now(), 10));
	}
	
}
