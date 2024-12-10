package andersen.dev.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andersen.dev.tickets.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	

//	public List<Ticket> getTicketsByUser(Integer userId) {
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
//		Root<Ticket> ticketRoot = cq.from(Ticket.class);
//		cq.select(ticketRoot).where(cb.equal(ticketRoot.get("user").get("id"), userId));
//		List<Ticket> tickets = entityManager.createQuery(cq).getResultList();
//		return tickets;
//	}
}
