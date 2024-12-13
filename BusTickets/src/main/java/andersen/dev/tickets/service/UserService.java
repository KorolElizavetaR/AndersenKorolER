package andersen.dev.tickets.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import andersen.dev.tickets.exception.UsertNotFoundException;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with name %s is not found", username)));
		return user;
	}
}
