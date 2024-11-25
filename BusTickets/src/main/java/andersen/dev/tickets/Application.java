package andersen.dev.tickets;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.ConnectionSupplier;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.service.TicketService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConnectionSupplier.class);
		TicketService ticketService = context.getBean(TicketService.class);
	
	}
	
}
