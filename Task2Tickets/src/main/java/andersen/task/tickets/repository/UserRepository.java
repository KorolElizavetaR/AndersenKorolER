package andersen.task.tickets.repository;

import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.user.User;

public class UserRepository {
	List<User> users;

	public UserRepository() {
		users = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public User getUserById(String id) throws InstanceNotFoundException {
		return users.stream().filter(user -> user.getID().equals(id)).findFirst()
				.orElseThrow(() -> new InstanceNotFoundException());
	}
}