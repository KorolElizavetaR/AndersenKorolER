package andersen.task.tickets.model.user;

import andersen.task.tickets.service.TicketService;
import andersen.task.tickets.util.Indexable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class User extends Indexable{
	protected final TicketService service;
	@Getter
	protected Roles role;
}
