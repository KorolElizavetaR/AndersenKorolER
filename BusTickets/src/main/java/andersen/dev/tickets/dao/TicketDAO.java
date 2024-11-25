package andersen.dev.tickets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.config.ConnectionSupplier;
import andersen.dev.tickets.model.Ticket;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TicketDAO {
	@Autowired
	private final ConnectionSupplier connSupplier;
	
	String dmlInsertTicket = "insert into ticket(ticket_id,user_id,\"type\", creation_date) values (?,?,?,?)";

	public void addTicketWithUser(Ticket ticket) throws SQLException {
		Connection connection = connSupplier.getConnection();
		PreparedStatement ps = connection.prepareStatement(dmlInsertTicket);
		ps.setString(1, ticket.getId());
		ps.setString(1, ticket.ge);
	}
	
}
