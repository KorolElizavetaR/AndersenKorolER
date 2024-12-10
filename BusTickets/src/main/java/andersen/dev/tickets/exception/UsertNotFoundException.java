package andersen.dev.tickets.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UsertNotFoundException extends NoSuchElementException{
	public UsertNotFoundException() {
		super("User is not found");
	}

}
