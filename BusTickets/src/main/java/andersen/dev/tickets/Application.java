package andersen.dev.tickets;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.BeanConfig;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.repository.TicketRepository;
import andersen.dev.tickets.service.TicketService;
import andersen.dev.tickets.service.UserService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		UserService userService = context.getBean(UserService.class);
		TicketService ticketService = context.getBean(TicketService.class);
		userService.getUserByIdWithoutTickets(2);
	}
	
}
