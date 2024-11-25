package andersen.dev.tickets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;

@Component
public class TicketDAO {
	
	String dmlInsertTicketWithUserID = "insert into ticket(ticket_id,user_id,\"type\", creation_date) values (?,?,?::ticket_type,?)";
	String dmlInsertTicket = "insert into ticket(ticket_id, \"type\", creation_date) values (?,?::ticket_type,?)";

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
	
}
