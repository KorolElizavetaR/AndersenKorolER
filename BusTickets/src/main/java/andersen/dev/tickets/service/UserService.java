package andersen.dev.tickets.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import andersen.dev.tickets.dto.UserDTO;
import andersen.dev.tickets.mapper.UserMapper;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public User addUser(User user) {
		return userRepository.addUser(user);
	}

	public Set<User> getUserByIdWithTickets(int id) {
		return userRepository.getUserByIdWithTickets(id);
	}

	public UserDTO getUserByIdWithoutTickets(int id) {
		return userMapper.getUserDTO(userRepository.getUserByIdWithoutTickets(id));
	}
	
	public boolean deleteUser(int id) {
		return userRepository.deleteUser(id);
	}
}
