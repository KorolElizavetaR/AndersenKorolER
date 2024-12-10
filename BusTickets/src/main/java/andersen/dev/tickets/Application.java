package andersen.dev.tickets;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.service.TicketService;
import andersen.dev.tickets.service.UserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		TicketService ticketService = context.getBean(TicketService.class);
		UserService userService = context.getBean(UserService.class);
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		
		User user = new User().setUsername("Vasya").setBpassword(encoder.encode("123"));
		Ticket ticket1 = new Ticket("AAA", TicketType.DAY, LocalDate.now(), 10).setUser(user);
		ticketService.insertTicket(ticket1);
		Ticket ticket2 = new Ticket("AVS", TicketType.DAY, LocalDate.now(), 12);
		ticketService.insertTicketWithExistingUser(ticket2, ticket1.getUser().getUserId());
		
		System.out.println(userService.getUserByIdWithTickets(ticket1.getUser().getUserId()));
	}

}
