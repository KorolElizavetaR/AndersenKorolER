package andersen.task.tickets.model.user;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.service.TicketService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Client extends User {
	private static final String PHONE_NUMBER_PATTERN = "\\+375\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}";
	@Pattern(regexp = PHONE_NUMBER_PATTERN)
	private String phone;
	private String email;
	@Setter
	private String ticketId;

	public Client(TicketService service) {
		super(service, Roles.ROLE_CLIENT);
	}

	public Client(TicketService service, String phone) {
		this(service);
		this.phone = phone;
	}

	public Client(TicketService service, String phone, String email) {
		this(service, phone);
		this.email = email;
	}

	public Ticket getTicket() throws InstanceNotFoundException {
		return service.getTicketByID(ticketId);
	}

	public String shareTicket() throws InstanceNotFoundException {
		if (ticketId == null || ticketId.isBlank())
			return "can't share with a ticket if you don't have one!";
		if (!(email == null || email.isBlank()))
			return getTicket().shared(phone, email);
		if (!(phone == null || phone.isEmpty()))
			return getTicket().shared(phone);
		else
			return "No phone number is registred";
	}

}
