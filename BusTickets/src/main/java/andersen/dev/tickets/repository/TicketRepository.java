package andersen.dev.tickets.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import andersen.dev.tickets.model.Ticket;
import lombok.Getter;
import lombok.Setter;

@Repository
public class TicketRepository {
	@Setter
	@Getter
	private List<Ticket> tickets = new ArrayList<>();

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
}
