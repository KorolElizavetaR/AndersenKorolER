package andersen.dev.tickets.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.repository.TicketRepository;
import andersen.dev.tickets.repository.ViolatedTicketRepository;
import andersen.dev.tickets.validator.TicketValidator;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
	@Autowired
	@Getter
	private TicketValidator validator;

	@Autowired
	private final TicketRepository repository;
	@Autowired
	@Getter
	private final ViolatedTicketRepository allTicketRepository;

	@PostConstruct
	public void init() throws IOException {
		for (Ticket ticket : allTicketRepository.getAllTickets()) {
			try {
				repository.addTicket(validator.validateTicket(ticket));
			} catch (ConstraintViolationException ex) {
				allTicketRepository.addViolation(ex.getLocalizedMessage());
			}
		}
	}

	public void addTicket(Ticket ticket) {
		repository.addTicket(validator.validateTicket(ticket));
	}

	public List<Ticket> getTickets() {
		return repository.getTickets();
	}

}
