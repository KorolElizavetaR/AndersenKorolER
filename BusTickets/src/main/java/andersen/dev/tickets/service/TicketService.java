package andersen.dev.tickets.service;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.SQLException;
=======
import java.io.IOException;
>>>>>>> master
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andersen.dev.tickets.model.Ticket;
<<<<<<< HEAD
import andersen.dev.tickets.model.TicketType;
import lombok.RequiredArgsConstructor;
import andersen.dev.tickets.config.ConnectionSupplier;
import andersen.dev.tickets.dao.TicketDAO;
=======
import andersen.dev.tickets.repository.TicketRepository;
import andersen.dev.tickets.repository.ViolatedTicketRepository;
import andersen.dev.tickets.validator.TicketValidator;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
>>>>>>> master

@Service
@RequiredArgsConstructor
public class TicketService {
	@Autowired
	private final ConnectionSupplier connSupplier;
	@Autowired
<<<<<<< HEAD
	private final TicketDAO ticketDAO;

	/**
	 * @param ticket is inserted into table
	 * @param userId adds person to ticket If userId is not in table, SQLException
	 *               is handled as a err message to user
	 */
	public void insertTicket(Ticket ticket, int userId) {
		try (Connection connection = connSupplier.getConnection()) {
			ticketDAO.addTicket(connection, ticket, userId);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
	}

	/**
	 * @param ticket inserted in database A small possibility of existing generated
	 *               key is ignored
	 */
	public void insertTicket(Ticket ticket) {
		try (Connection connection = connSupplier.getConnection()) {
			ticketDAO.addTicket(connection, ticket);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
=======
	private final TicketRepository repository;
	@Autowired
	@Getter
	private final ViolatedTicketRepository allTicketRepository;

	@PostConstruct
	public void init() throws IOException {
		for (Ticket ticket : allTicketRepository.getAllTickets()) {
			try {
				repository.addTicket(validator.validateTicket(ticket));
			} catch (ConstraintViolationException ex) {
				allTicketRepository.addViolation(ex.getLocalizedMessage());
			}
		}
	}

	public void addTicket(Ticket ticket) {
		repository.addTicket(validator.validateTicket(ticket));
>>>>>>> master
	}

	public Ticket getTicket(String ticketId) {
		try (Connection connection = connSupplier.getConnection()) {
			return ticketDAO.getTicket(connection, ticketId);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		return null;
	}

	public Ticket getTicketWithUser(String ticketId) {
		try (Connection connection = connSupplier.getConnection()) {
			Ticket ticket = ticketDAO.getTicketWithUser(connection, ticketId);
			return ticket;
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		return null;
	}

	public List<Ticket> getTicketsByUserId(int userId) {
		try (Connection connection = connSupplier.getConnection()) {
			return ticketDAO.getTicketsByUserId(connection, userId);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		return null;
	}

	/**
	 * @return the row count for SQL Data Manipulation Language (DML) statementsor;
	 *         -1 if exception is thrown
	 */
	public int updateTicketType(String ticketId, TicketType type) {
		try (Connection connection = connSupplier.getConnection()) {
			return ticketDAO.updateTicketType(connection, ticketId, type);
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		return -1;
	}
}
