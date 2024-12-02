package andersen.dev.tickets.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenConstraint implements ConstraintValidator<Even, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value % 2 == 0;
	}

}
