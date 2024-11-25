package andersen.dev.tickets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.User;

@Component
public class UserDAO {
	private String dmlInsertUser = "insert into user(name, creation_date) values (?, ?)";
	private String dqlGetUser = "select * from user where user_id = ?";
	private String dmlDeleteUser = "DELETE FROM \"user\" WHERE user_id = ?";
	
	
	public void addUser(Connection connection, User user) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dmlInsertUser);
		ps.setString(1, user.getName());
		ps.setTimestamp(2, Timestamp.valueOf(user.getCreationDate()));
		ps.executeUpdate();
	}
	
	public User getUserById(Connection connection, Integer id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dqlGetUser);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return userFromResultSet(rs);
	}
	
	/*
	 * Delete connected tickets because of CASCADE delete;
	 */
	public int deleteUser(Connection connection, Integer id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dmlDeleteUser);
		ps.setInt(1, id);
		return ps.executeUpdate();
	}
	
	public static User userFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime()).
		setName(rs.getString("name")).setUserId(rs.getInt("user_id"));
		return user;
	}
}
