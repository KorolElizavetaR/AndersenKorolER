package andersen.task.tickets.model.user;

import andersen.task.tickets.model.Indexable;
import andersen.task.tickets.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class User extends Indexable{
	protected final TicketService service;
	@Getter
	protected String role;
}
