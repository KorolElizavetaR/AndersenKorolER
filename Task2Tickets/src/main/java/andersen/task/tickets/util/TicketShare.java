package andersen.task.tickets.util;

import andersen.task.tickets.model.Ticket;

public class TicketShare {
	public static String shared(Ticket ticket, String phone) {
		return ticket + " is shared via phone number:" + phone;
	}

	public static String shared(Ticket ticket, String phone, String email) {
		return shared(ticket, phone) + " and email: " + email;
	}
}
