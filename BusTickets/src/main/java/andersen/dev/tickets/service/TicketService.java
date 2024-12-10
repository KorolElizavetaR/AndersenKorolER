package andersen.dev.tickets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import andersen.dev.tickets.exception.TicketNotFoundException;
import andersen.dev.tickets.exception.UsertNotFoundException;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.repository.TicketRepository;
import andersen.dev.tickets.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketService {
	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	
	@Transactional(readOnly = false)
	public Ticket insertTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	@Transactional(readOnly = false)
	public Ticket insertTicketWithExistingUser(Ticket ticket, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new UsertNotFoundException());
		ticket.setUser(user);
		return ticketRepository.save(ticket);
	}

	public Ticket getTicket(Integer ticketId) {
		return ticketRepository.findById(ticketId).orElseThrow(()->new TicketNotFoundException());
	}

	@Transactional(readOnly = false)
	public Ticket updateTicketType(Integer ticketId, TicketType type) {
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()->new TicketNotFoundException());
		ticket.setTicketType(type);
		return ticketRepository.save(ticket);
	}
}
