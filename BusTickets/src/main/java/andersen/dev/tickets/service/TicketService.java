package andersen.dev.tickets.service;

import java.util.List;

import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketService {
	private final TicketRepository ticketRepository;

	public Ticket insertTicket(Ticket ticket) {
		return ticketRepository.addTicket(ticket);
	}

	public Ticket getTicket(Integer ticketId) {
		return ticketRepository.getTicketById(ticketId);
	}

	public List<Ticket> getTicketsByUserId(int userId) {
		return ticketRepository.getTicketsByUser(userId);
	}

	public Ticket updateTicketType(Integer ticketId, TicketType type) {
		return ticketRepository.updateTicketType(ticketId, type);
	}
}
