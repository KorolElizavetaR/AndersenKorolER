package andersen.task.tickets.exception;

public class TicketNotFoundException extends RuntimeException {
	public TicketNotFoundException(String userId) {
		super(String.format("Ticket with id %s is not found.", userId));
	}
}
