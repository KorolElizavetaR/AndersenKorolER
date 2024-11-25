package andersen.dev.tickets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import andersen.dev.tickets.model.User;

public class UserDAO {
	String dmlInsertUser = "insert into user(name, creation_date) values (?, ?)";

	public void addUser(Connection connection, User user) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dmlInsertUser);
		ps.setString(1, user.getName());
		ps.setTimestamp(2, Timestamp.valueOf(user.getCreationDate()));
		ps.executeUpdate();
	}
}
