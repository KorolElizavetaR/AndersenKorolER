package andersen.task.tickets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.user.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public class UserService {
	List<User> users;
	
	public UserService() {
		users = new ArrayList<User>();
	}
	
	public User addUser(User user) {
		try {
			Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator()
					.validate(user);
			if (!violations.isEmpty())
				throw new ConstraintViolationException(violations);
			users.add(user);
		} catch (ConstraintViolationException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return user;
	}
	
	public User getUserById(String id) throws InstanceNotFoundException
	{
		return users.stream().filter(user -> user.getID().equals(id)).findFirst()
				.orElseThrow(() -> new InstanceNotFoundException());
	}
}
