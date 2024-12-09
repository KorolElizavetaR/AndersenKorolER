package andersen.task.tickets.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String userId) {
		super(String.format("User with id %s is not found.", userId));
	}
}
