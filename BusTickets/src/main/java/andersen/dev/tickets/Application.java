package andersen.dev.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import andersen.dev.tickets.service.TicketDAO;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		TicketDAO service = context.getBean(TicketDAO.class);
		service.getValidator().getViolatedFieldsCounter()
		.forEach((field, counter) -> System.out.println(String.format("%s : %s violations", field, counter)));
		System.out.println("Total = " + service.getValidator().getTicketCounterBeforeCheckingViolations());
		System.out.println("Valid = " + service.getTickets().size());
		service.getTickets().stream().forEach(System.out::println);
	}
	
}
