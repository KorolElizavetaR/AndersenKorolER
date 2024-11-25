package andersen.dev.tickets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import andersen.dev.tickets.model.User;

@Component
public class TicketDAO {

	String dmlInsertTicketWithUserID = "insert into ticket(ticket_id,user_id,\"type\", ticket_creation_date) values (?,?,?::ticket_type,?)";
	String dmlInsertTicket = "insert into ticket(ticket_id, \"type\", creation_date) values (?,?::ticket_type,?)";
	String dqlFetchTicketById = "select * from ticket where ticket_id = ?";
	String dqlFetchTicketByIdWithUser = "select * from ticket LEFT JOIN \"user\" ON  ticket.user_id = \"user\".user_id where ticket_id = ?";
	

	public void addTicket(Connection connection, Ticket ticket, int userId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dmlInsertTicketWithUserID);
		ps.setString(1, ticket.getId());
		ps.setInt(2, userId);
		ps.setString(3, ticket.getTicketType().getType());
		ps.setTimestamp(4, Timestamp.valueOf(ticket.getCreationDate()));
		ps.executeUpdate();
	}

	public void addTicket(Connection connection, Ticket ticket) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dmlInsertTicket);
		ps.setString(1, ticket.getId());
		ps.setString(2, ticket.getTicketType().getType());
		ps.setTimestamp(3, Timestamp.valueOf(ticket.getCreationDate()));
		ps.executeUpdate();
	}

	public Ticket getTicket(Connection connection, String ticketId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dqlFetchTicketById);
		ps.setString(1, ticketId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return ticketFromResultSet(rs);
	}
	
	public Ticket getTicketWithUser(Connection connection, String ticketId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(dqlFetchTicketByIdWithUser);
		ps.setString(1, ticketId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Ticket tc = ticketFromResultSet(rs);
		tc.setUser(UserDAO.userFromResultSet(rs));
		return tc;
	}
	
	public static Ticket ticketFromResultSet(ResultSet rs) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.
			setCreationDate(rs.getTimestamp("ticket_creation_date").toLocalDateTime()).
			setTicketType(TicketType.valueOf(rs.getString("type"))).
			setId(rs.getString("ticket_id"));
		return ticket;
	}
}
