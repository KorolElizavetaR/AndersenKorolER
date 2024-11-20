package andersen.task.tickets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.user.User;
import andersen.task.tickets.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	private final UserRepository repository;

	public User addUser(User user) {
		try {
			Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator()
					.validate(user);
			if (!violations.isEmpty())
				throw new ConstraintViolationException(violations);
			repository.addUser(user);
		} catch (ConstraintViolationException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return user;
	}

	public User getUserById(String id) throws InstanceNotFoundException {
		return repository.getUserById(id);
	}
}
