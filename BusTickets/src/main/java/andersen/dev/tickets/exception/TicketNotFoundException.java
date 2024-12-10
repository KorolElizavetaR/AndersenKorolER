package andersen.dev.tickets.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends NoSuchElementException{
	public TicketNotFoundException() {
		super("Ticket is not found");
	}

}
