package andersen.dev.tickets.constraint;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.TicketType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateForTicketTypeConstraint implements ConstraintValidator<ValidDateForTicketType, Ticket> {

	@Override
	public boolean isValid(Ticket ticket, ConstraintValidatorContext context) {
			return !(ticket.getTicketType() == TicketType.PRIME && ticket.getStartDate() != null);
	}
}
