package andersen.dev.tickets.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.config.ConnectionSupplier;
import andersen.dev.tickets.dao.UserDAO;
import andersen.dev.tickets.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService {
	@Autowired
	private final ConnectionSupplier connSupplier;
	@Autowired
	private final UserDAO userDAO;

	public void addUser(User user) {
		try (Connection connection = connSupplier.getConnection()) {
			userDAO.addUser(connection, user);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
	}

	public User getUserById(int id) {
		try (Connection connection = connSupplier.getConnection()) {
			return userDAO.getUserById(connection, id);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		return null;
	}

	public int deleteUser(int id) {
		try (Connection connection = connSupplier.getConnection()) {
			return userDAO.deleteUser(connection, id);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		return -1;
	}
}
