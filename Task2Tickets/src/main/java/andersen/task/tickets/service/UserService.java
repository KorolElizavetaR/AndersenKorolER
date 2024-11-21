package andersen.task.tickets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.exception.TicketNotFoundException;
import andersen.task.tickets.exception.UserNotFoundException;
import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.model.user.User;
import andersen.task.tickets.repository.TicketRepository;
import andersen.task.tickets.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;

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

	public User getUserById(String id) throws InstanceNotFoundException {
		return userRepository.getUserById(id).orElseThrow(()->new UserNotFoundException(id));
	}
	
	public Ticket shareTicket(String userId, String ticketId) {
		User user = userRepository.getUserById(userId).orElseThrow(()->new UserNotFoundException(id));
		Ticket ticket = user.getTickets().stream().filter(ticket -> ticket.getID().equals(ticketId)).findFirst().orElseThrow(() -> new TicketNotFoundException(userId));
		if (!(user.ge == null || email.isBlank()))
			return getTicket().shared(phone, email);
		if (!(phone == null || phone.isEmpty()))
			return getTicket().shared(phone);
		else
			return "No phone number is registred";
	}
}
