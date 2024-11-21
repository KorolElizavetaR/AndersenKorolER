package andersen.task.tickets.service;

import java.util.Set;

import andersen.task.tickets.exception.TicketNotFoundException;
import andersen.task.tickets.exception.UserNotFoundException;
import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.model.User;
import andersen.task.tickets.repository.TicketRepository;
import andersen.task.tickets.repository.UserRepository;
import andersen.task.tickets.util.Printable;
import andersen.task.tickets.util.TicketShare;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService implements Printable {
	private final UserRepository userRepository;
	
	public User addUser(User user) {
		try {
			Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator()
					.validate(user);
			if (!violations.isEmpty())
				throw new ConstraintViolationException(violations);
			userRepository.addUser(user);
		} catch (ConstraintViolationException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return user;
	}

	public User getUserById(String id) {
		return userRepository.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	public void addTicketToUser(String userId, String ticketId) {
		User user = userRepository.getUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		Ticket userTicket = user.getTickets().stream().filter(ticket -> ticket.getID().equals(ticketId)).findFirst()
				.orElseThrow(() -> new TicketNotFoundException(ticketId));
		userRepository.addTicket(user, userTicket);
	}
	
	public void addTicketToUser(String userId, Ticket ticket) {
		User user = userRepository.getUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		userRepository.addTicket(user, ticket);
	}
	
	public void addTicketToUser(User user, Ticket ticket) {
		userRepository.addTicket(user, ticket);
	}

	public String shareTicket(String userId, String ticketId) {
		User user = userRepository.getUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		Ticket userTicket = user.getTickets().stream().filter(ticket -> ticket.getID().equals(ticketId)).findFirst()
				.orElseThrow(() -> new TicketNotFoundException(ticketId));
		if (!(user.getEmail() == null || user.getEmail().isBlank())) {
			return TicketShare.shared(userTicket, user.getPhone(), user.getEmail());
		}
		if (!(user.getPhone() == null || user.getPhone().isEmpty())) {
			return TicketShare.shared(userTicket, user.getPhone());
		} else {
			return "No phone number or email is registered";
		}
	}
	
	@Override
	public void printMethods() {
		System.out.println("From UserService!");
		Printable.super.printMethods();
	}
}
