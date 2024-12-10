package andersen.dev.tickets;

import java.io.IOException;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.User;
import andersen.dev.tickets.service.UserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		UserService service = context.getBean(UserService.class);
		service.addUser(new User().setUsername("TEST2")
				.setTickets(Collections.singletonList(new Ticket().setTicketName("AAA"))));
	}

}
