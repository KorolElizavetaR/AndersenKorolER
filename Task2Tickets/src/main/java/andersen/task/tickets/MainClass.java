package andersen.task.tickets;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.ticket.SectorHall;
import andersen.task.tickets.model.ticket.Ticket;
import andersen.task.tickets.model.user.Admin;
import andersen.task.tickets.model.user.Client;
import andersen.task.tickets.service.TicketService;
import andersen.task.tickets.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class MainClass {
	List<Ticket> tickets = new ArrayList<>();

	public static void main(String[] args) throws InstanceNotFoundException {
		TicketService service = new TicketService();
		Admin admin = new Admin(service);
		String adminId = admin.getID();
		Client client1 = new Client(service);
		Client client2 = new Client(service, "+375(29)123-45-67");
		String client2Id = client2.getID();

		UserService userService = new UserService();
		userService.addUser(admin);
		userService.addUser(client1);
		userService.addUser(client2);
		/*
		 * Here i'm imitating the real process of getting something out of DB (even if
		 * variables actually hold the same link)
		 */
		Admin adminFromId = (Admin) userService.getUserById(adminId);
		Client clientFromId = (Client) userService.getUserById(client2Id);
		
		Ticket ticket0 = new Ticket("TEST0", 397, LocalDateTime.of(2024, 11, 25, 13, 30));
		
		adminFromId.addTicket(ticket0);
		adminFromId.addTicket(new Ticket("TEST1", 221, LocalDateTime.of(2024, 11, 10, 19, 30)));
		adminFromId.addTicket(new Ticket("55555555555555555", 345, LocalDateTime.of(2024, 12, 17, 23, 00)));
		adminFromId.addTicket(new Ticket("TEST2", 345, LocalDateTime.of(2020, 12, 17, 23, 00)));
		adminFromId.checkTickets();
		String ticketIdFromAdmin = adminFromId.getTicketById(ticket0.getID()).getID();
		
		clientFromId.setTicketId(ticketIdFromAdmin);
		System.out.println(clientFromId.shareTicket()); 

		ticket0.printer();
		service.printer();
	}

}
