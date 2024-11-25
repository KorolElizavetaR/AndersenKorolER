package andersen.task.tickets;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.model.User;
import andersen.task.tickets.model.enumeration.Roles;
import andersen.task.tickets.repository.TicketRepository;
import andersen.task.tickets.repository.UserRepository;
import andersen.task.tickets.service.TicketService;
import andersen.task.tickets.service.UserService;

public class Application {
	List<Ticket> tickets = new ArrayList<>();

	public static void main(String[] args) throws InstanceNotFoundException {
		TicketRepository ticketRepository = new TicketRepository();
		TicketService ticketService = new TicketService(ticketRepository);
		UserService userService = new UserService(new UserRepository());
		User user = userService.addUser(new User());
		User admin = userService.addUser(new User("+375(29)123-45-67", "example@gmail.com", Roles.ROLE_ADMIN));

		Ticket ticket0 = ticketService.addTicket(new Ticket("TEST0", "397", LocalDateTime.of(2024, 11, 25, 13, 30)));
		Ticket ticket1 = ticketService.addTicket(new Ticket("TEST1", "221", LocalDateTime.of(2024, 11, 10, 19, 30)));
		ticketService.addTicket(new Ticket("55555555555555555", "221", LocalDateTime.of(2024, 11, 10, 19, 30)));
		
		userService.addTicketToUser(user, ticket0);
		userService.addTicketToUser(admin.getID(), ticket1);
		
		userService.printMethods();
	}

}
