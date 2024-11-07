package andersen.task.tickets.model.user;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.ticket.Ticket;
import andersen.task.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;

public class Admin extends User {

	public Admin(TicketService service) {
		super(service, "Admin");
	}

	public void checkTickets() {
		service.getAllTickets().stream().forEach(System.out::println);
	}

	public void addTicket(Ticket ticket) {
		System.out.println(service.addTicket(ticket) + " is succesfully added");
	}

	public Ticket getTicketById(String id) throws InstanceNotFoundException {
		return service.getTicketByID(id);
	}
}
