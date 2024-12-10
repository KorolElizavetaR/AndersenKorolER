package andersen.dev.tickets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import andersen.dev.tickets.exception.UsertNotFoundException;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;

	@Transactional(readOnly = false)
	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User getUserByIdWithTickets(int id) {
		return userRepository.findUserWithTicketsById(id).orElseThrow(() -> new UsertNotFoundException());
	}

	public User getUserByIdWithoutTickets(int id) {
		return userRepository.findById(id).orElseThrow(() -> new UsertNotFoundException());
	}

	@Transactional(readOnly = false)
	public void deleteUser(int id) {
		User user = userRepository.findUserWithTicketsById(id).orElseThrow(() -> new UsertNotFoundException());
		userRepository.delete(user);
	}
}
