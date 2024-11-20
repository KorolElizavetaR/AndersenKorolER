package andersen.dev.tickets.repository;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import lombok.Getter;
import lombok.Setter;

@Component
/**
 * 	Normally we would use singleton, however, by the given task 
 * 	we should be able to count all wrong tickets. 
 * 	During a runtime of a program, two instances are created:
 * 	1. For all tickets
 *  2. For validated tickets
 */
@Scope (ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicketRepository {
	@Setter
	@Getter
	private List<Ticket> tickets;
	
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
}
