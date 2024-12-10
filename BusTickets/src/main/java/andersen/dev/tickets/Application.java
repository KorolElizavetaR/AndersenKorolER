package andersen.dev.tickets;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.BeanConfig;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.parser.TicketParser;
import andersen.dev.tickets.service.TicketService;
import andersen.dev.tickets.service.UserService;
import andersen.dev.tickets.util.TicketFromArrayToDB;
import andersen.dev.tickets.validator.TicketValidator;
import jakarta.validation.ConstraintViolationException;

public class Application {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		UserService userService = context.getBean(UserService.class);
		TicketService ticketService = context.getBean(TicketService.class);
		TicketFromArrayToDB ticketFromArrayToDB = context.getBean(TicketFromArrayToDB.class);
		TicketParser ticketParser = context.getBean(TicketParser.class);

		List<Ticket> tickets = ticketParser.fromJsonToTicket();
		ticketFromArrayToDB.insertTicketsToDatabase(tickets);
		context.close();
	}

}
