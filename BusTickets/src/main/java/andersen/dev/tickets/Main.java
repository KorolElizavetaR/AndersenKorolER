package andersen.dev.tickets;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;


public class Main {
	private final static String FILEPATH = "src/main/resources/tickets.json";

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Main.class, args);
	}
}
