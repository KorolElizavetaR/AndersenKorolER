package andersen.dev.tickets.validator;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateDateForTicketType implements ConstraintValidator<ValidDateForTicketType, Ticket> {

	@Override
	public boolean isValid(Ticket ticket, ConstraintValidatorContext context) {
		if (ticket.getTicketType() == TicketType.PRIME && ticket.getStartDate() != null)
			return false;
		return true;
	}
}
