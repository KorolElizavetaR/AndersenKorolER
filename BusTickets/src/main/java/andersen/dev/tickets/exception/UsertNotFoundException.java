package andersen.dev.tickets.exception;

import java.util.NoSuchElementException;

public class UsertNotFoundException extends NoSuchElementException{
	public UsertNotFoundException() {
		super("User is not found");
	}

}
