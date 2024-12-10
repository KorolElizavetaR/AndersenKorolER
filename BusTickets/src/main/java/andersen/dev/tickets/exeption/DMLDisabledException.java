package andersen.dev.tickets.exeption;

public class DMLDisabledException extends RuntimeException {
	public DMLDisabledException() {
		super("DML operations are not supported");
	}
}
