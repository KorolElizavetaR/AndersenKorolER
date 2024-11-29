package andersen.dev.tickets.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import andersen.dev.tickets.config.SessionSupplier;
import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TicketRepository {
	private final SessionSupplier supplier;

	/**
	 * Add ticket to database. If Ticket instance contains a non null user
	 * field, persists user with ticket
	 * @return saved ticket
	 */
	public Ticket addTicket(Ticket ticket) {
		Session session = supplier.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(ticket);
		transaction.commit();
		session.close();
		return ticket;
	}

	public Ticket getTicketById(Integer ticketId) {
		Session session = supplier.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Ticket ticket = session.get(Ticket.class, ticketId);
		transaction.commit();
		session.close();
		return ticket;
	}

	public Ticket updateTicketType(Integer ticketId, TicketType type) {
		Session session = supplier.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Ticket ticket = session.get(Ticket.class, ticketId);
		ticket.setTicketType(type);
		transaction.commit();
		session.close();
		return ticket;
	}

	public List<Ticket> getTicketsByUser(Integer userId) {
		Session session = supplier.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
		Root<Ticket> ticketRoot = cq.from(Ticket.class);
		cq.select(ticketRoot).where(cb.equal(ticketRoot.get("user").get("id"), userId));
		List<Ticket> tickets = session.createQuery(cq).getResultList();
		transaction.commit();
		session.close();
		return tickets;
	}
}
