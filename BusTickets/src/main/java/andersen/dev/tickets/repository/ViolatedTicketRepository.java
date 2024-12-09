package andersen.dev.tickets.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.parser.TicketParser;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;

@Repository
public class ViolatedTicketRepository {
	/**
	 *  All tickets : valid and invalid
	 */
	@Setter
	@Getter
	private List<Ticket> allTickets;
	@Autowired
	private TicketParser parser;
	/**
	 * 	Stores violations as a messages
	 */
	@Getter
	private List<String> violations = new ArrayList<>();
	
	@PostConstruct
	public void init() throws IOException {
		allTickets = parser.fromJsonToTicket();
	}
	
	public void addViolation(String msg) {
		violations.add(msg);
	}
}
