package andersen.dev.tickets.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import lombok.RequiredArgsConstructor;
import andersen.dev.tickets.config.ConnectionSupplier;
import andersen.dev.tickets.dao.TicketDAO;

@Component
@RequiredArgsConstructor
public class TicketService {
	@Autowired
	private final ConnectionSupplier connSupplier;
	@Autowired
	private final TicketDAO ticketDAO;

	/**
	 * @param ticket is inserted into table
	 * @param userId adds person to ticket
	 * If userId is not in table, SQLException is handled as a message to user
	 */
	public void insertTicket(Ticket ticket, int userId) {
		try (Connection connection = connSupplier.getConnection()) {
			ticketDAO.addTicket(connection, ticket, userId);
		} catch (SQLException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
	}

	/**
	 * @param ticket inserted in database
	 * A small possibility of existing generated key is ignored 
	 */
	public void insertTicket(Ticket ticket) {
		try (Connection connection = connSupplier.getConnection()) {
			ticketDAO.addTicket(connection, ticket);
		} catch (SQLException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
	}

}
