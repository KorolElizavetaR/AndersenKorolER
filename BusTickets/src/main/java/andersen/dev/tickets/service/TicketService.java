package andersen.dev.tickets.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.parser.TicketParser;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

@Component
public class TicketService {
	@Autowired
	TicketParser parser;
	List <Ticket> tickets = new ArrayList<>();
	// добавь мапу для отслеживания ошибок
	
	// later handle the exception
	@PostConstruct 
	public void init() throws IOException {
		parser.fromJsonToTicket().stream().forEach(ticket -> addTicket(ticket));;
	}
	
	public void addTicket (Ticket ticket) {
		Set<ConstraintViolation<Ticket>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(ticket);
		if (!violations.isEmpty())
		{
			// handle this.
			//throw new ConstraintViolationException(violations);
			System.out.println("violation!");
			return;
		}
		tickets.add(ticket);
	}
	
	public List<Ticket> getTickets(){
		return this.tickets;
	}
}
