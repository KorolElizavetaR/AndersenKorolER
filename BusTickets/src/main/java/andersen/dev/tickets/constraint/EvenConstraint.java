package andersen.dev.tickets.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import andersen.dev.tickets.model.Ticket;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EvenConstraint implements ConstraintValidator<Even, Integer>{

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value % 2 == 0)
			return true;
		return false;
	}

}
