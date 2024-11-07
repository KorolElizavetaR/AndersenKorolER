package andersen.dev.tickets.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DateForTicketTypeConstraint.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateForTicketType {
	String message() default "If ticket type = PRIME, no startDate required";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
