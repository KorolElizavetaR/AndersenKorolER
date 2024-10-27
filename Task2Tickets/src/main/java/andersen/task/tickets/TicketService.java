package andersen.task.tickets;

import com.mifmif.common.regex.Generex;

import andersen.task.tickets.model.Ticket;

public class TicketService {
	
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		System.out.println(ticket.getTicketID());
	}
}
