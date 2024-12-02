package andersen.task.tickets.model;

public interface ContentPrinter {
	default void printer() {
		System.out.println("print content in console");
	}
}
