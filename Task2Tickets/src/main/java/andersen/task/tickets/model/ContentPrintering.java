package andersen.task.tickets.model;

public interface ContentPrintering {
	default void printer() {
		System.out.println("print content in console");
	}
}
