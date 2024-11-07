package andersen.dev.tickets.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.parser.TicketParser;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import lombok.Getter;

@Component
public class TicketValidator {
	List<Ticket> allTickets;
	@Autowired
	private TicketParser parser;
	/**
	 * 	Important note: if field, for example, breaks several constraints,
	 * 	it is considered as one violation.
	 * 	If price = -17, it technically breaks two violations, but considered as one
	 */
	@Getter
	private Map<String, Integer> violatedFieldsCounter = new HashMap<>();
	@Getter
	private int ticketCounterBeforeCheckingViolations;
	
	@PostConstruct
	public void init() throws IOException{
		allTickets = parser.fromJsonToTicket();
	}

	public List<Ticket> getValidTickets() throws IOException {
		List<Ticket> validTickets = new ArrayList<>();
		ticketCounterBeforeCheckingViolations = allTickets.size();
		for (Ticket ticket : allTickets) {
			try {
				validTickets.add(addTicket(ticket));
			} catch (ConstraintViolationException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return validTickets;
	}
	
	public Ticket addTicket(Ticket ticket) {
		Set<ConstraintViolation<Ticket>> violations = Validation.buildDefaultValidatorFactory().getValidator()
				.validate(ticket);
		Set<String> violatedFields = violations.stream().map(ConstraintViolation::getPropertyPath).map(Path::toString)
				.collect(Collectors.toSet());
		if (!violations.isEmpty()) {
			StringBuilder errorMsg = new StringBuilder(ticket + " is violating given conditions:\n\t");
			violatedFields.forEach(field -> {
				violatedFieldsCounter.put(field, violatedFieldsCounter.getOrDefault(field, 0) + 1);
			});
			violations.forEach(violation -> errorMsg.append(violation.getPropertyPath()).append(" " + violation.getMessage()).append("\n\t"));
			throw new ConstraintViolationException(errorMsg.toString(), violations);
		}
		return ticket;
	}
}
