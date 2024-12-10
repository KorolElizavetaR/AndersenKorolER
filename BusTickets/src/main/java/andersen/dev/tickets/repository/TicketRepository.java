package andersen.dev.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andersen.dev.tickets.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
