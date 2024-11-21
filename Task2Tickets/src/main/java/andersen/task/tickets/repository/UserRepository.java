package andersen.task.tickets.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.model.User;

public class UserRepository {
	List<User> users = new ArrayList<>();
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public Optional<User> getUserById(String id) {
		return users.stream().filter(user -> user.getID().equals(id)).findFirst();
	}
	
	public void addTicket(User user, Ticket ticket) {
		user.getTickets().add(ticket);
	}
}
