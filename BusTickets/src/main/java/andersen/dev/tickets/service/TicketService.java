package andersen.dev.tickets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.parser.TicketParser;

public class TicketService {
	@Autowired
	TicketParser parser;
	List <Ticket> tickets;
	
	public TicketService() {
		
	}
	
	public void addTicket (Ticket ticket) {
		
	}
}
