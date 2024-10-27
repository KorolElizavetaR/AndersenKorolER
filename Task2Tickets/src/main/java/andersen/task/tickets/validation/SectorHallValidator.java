package andersen.task.tickets.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SectorHallValidator implements ConstraintValidator<SectorHallConstraint, Character> {

	@Override
	public boolean isValid(Character value, ConstraintValidatorContext context) {
		return (value == 'A' || value == 'B' || value == 'C' || value == '\u0000');
	}

}
