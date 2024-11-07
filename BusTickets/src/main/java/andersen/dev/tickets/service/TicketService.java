package andersen.dev.tickets.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.parser.TicketParser;
import andersen.dev.tickets.validator.TicketValidator;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import lombok.Getter;
import lombok.Setter;

@Component
public class TicketService {
	@Autowired
	@Getter
	private TicketValidator validator;
	private List<Ticket> tickets;

	@PostConstruct
	public void init() throws IOException {
		tickets = validator.getValidTickets();
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

}
