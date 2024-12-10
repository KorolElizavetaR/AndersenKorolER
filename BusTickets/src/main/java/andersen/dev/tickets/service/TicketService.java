package andersen.dev.tickets.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import andersen.dev.tickets.aspect.annotation.EnableDML;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketService {
	private final TicketRepository ticketRepository;
	
	@EnableDML
	@Transactional(readOnly = false)
	public Ticket insertTicket(Ticket ticket) {
		return ticketRepository.addTicket(ticket);
	}

	public Ticket getTicket(Integer ticketId) {
		return ticketRepository.getTicketById(ticketId);
	}

	public List<Ticket> getTicketsByUserId(int userId) {
		return ticketRepository.getTicketsByUser(userId);
	}

	@EnableDML
	@Transactional(readOnly = false)
	public Ticket updateTicketType(Integer ticketId, TicketType type) {
		return ticketRepository.updateTicketType(ticketId, type);
	}
}
