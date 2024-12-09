package andersen.dev.tickets.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TicketRepository {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Add ticket to database. If Ticket instance contains a non null user field,
	 * persists user with ticket
	 * 
	 * @return saved ticket
	 */
	public Ticket addTicket(Ticket ticket) {
		//Session session = entityManager.getCurrentSession();
		entityManager.persist(ticket);
		return ticket;
	}

	public Ticket getTicketById(Integer ticketId) {
		Ticket ticket = entityManager.find(Ticket.class, ticketId);
		return ticket;
	}

	public Ticket updateTicketType(Integer ticketId, TicketType type) {
		//Session session = entityManager.getCurrentSession();
		Ticket ticket = entityManager.find(Ticket.class, ticketId);
		ticket.setTicketType(type);
		//session.close();
		return ticket;
	}

	public List<Ticket> getTicketsByUser(Integer userId) {
	//	Session session = entityManager.getCurrentSession();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
		Root<Ticket> ticketRoot = cq.from(Ticket.class);
		cq.select(ticketRoot).where(cb.equal(ticketRoot.get("user").get("id"), userId));
		List<Ticket> tickets = entityManager.createQuery(cq).getResultList();
	//	session.close();
		return tickets;
	}
}
