package andersen.dev.tickets.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import andersen.dev.tickets.model.Ticket;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketValidator {

	public Ticket validateTicket(Ticket ticket) throws ConstraintViolationException {
		Set<ConstraintViolation<Ticket>> violations = Validation.buildDefaultValidatorFactory().getValidator()
				.validate(ticket);
		Set<String> violatedFields = violations.stream().map(ConstraintViolation::getPropertyPath).map(Path::toString)
				.collect(Collectors.toSet());
		Map<String, Integer> violatedFieldsCounter = new HashMap<>();
		if (!violations.isEmpty()) {
			StringBuilder errorMsg = new StringBuilder(ticket + " is violating given conditions:\n\t");
			violatedFields.forEach(field -> {
				violatedFieldsCounter.put(field, violatedFieldsCounter.getOrDefault(field, 0) + 1);
			});
			violations.forEach(violation -> errorMsg.append(violation.getPropertyPath())
					.append(" " + violation.getMessage()).append("\n\t"));
			throw new ConstraintViolationException(errorMsg.toString(), violations);
		}
		return ticket;
	}
}
