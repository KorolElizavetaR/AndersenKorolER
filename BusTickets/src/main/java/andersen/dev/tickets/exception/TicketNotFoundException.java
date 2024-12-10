package andersen.dev.tickets.exception;

import java.util.NoSuchElementException;

public class TicketNotFoundException extends NoSuchElementException{
	public TicketNotFoundException() {
		super("Ticket is not found");
	}

}
