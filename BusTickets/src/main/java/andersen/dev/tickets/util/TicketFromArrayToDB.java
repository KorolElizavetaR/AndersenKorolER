package andersen.dev.tickets.util;

import java.util.List;

import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.service.TicketService;
import andersen.dev.tickets.validator.TicketValidator;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

/**
 * If we make TicketValidator and TicketService inherited from interfaces
 * Validator and Service, we can make this method work for any data (aka for
 * User and etc)
 * 
 * But for now it works just for tickets
 */
@Component
@RequiredArgsConstructor
public class TicketFromArrayToDB {
	private final TicketValidator ticketValidator;
	private final TicketService ticketService;

	public void insertTicketsToDatabase(List<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			try {
				Ticket parsedTicket = ticketValidator.validateTicket(ticket);
				ticketService.insertTicket(parsedTicket);
			} catch (ConstraintViolationException e) {
				e.printStackTrace();
			}
		}
	}
}
