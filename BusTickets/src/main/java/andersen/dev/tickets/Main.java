package andersen.dev.tickets;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import andersen.dev.tickets.service.TicketService;

@SpringBootApplication
public class Main {
	//private final static String FILEPATH = "src/main/resources/tickets.json";

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Main.class, args);
		TicketService service = context.getBean(TicketService.class);
		service.getTickets().stream().forEach(System.out::println);
	}
}
