package andersen.dev.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import andersen.dev.tickets.config.ConnectionSupplier;
import andersen.dev.tickets.service.TicketService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConnectionSupplier.class);
	}
	
}
