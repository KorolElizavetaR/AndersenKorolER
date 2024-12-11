package andersen.dev.tickets;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.BeanConfig;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.service.TicketService;
import andersen.dev.tickets.service.UserService;

public class Application {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		UserService userService = context.getBean(UserService.class);
		TicketService ticketService = context.getBean(TicketService.class);
		Ticket ticket = new Ticket("AAB", TicketType.DAY, LocalDate.now(), 10);
		ticketService.insertTicket(ticket);
		context.close();
	}

}
