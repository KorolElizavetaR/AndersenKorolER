package andersen.dev.tickets.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EvenConstraint.class)
@Target({ ElementType.FIELD })
@Retention (RetentionPolicy.RUNTIME)
public @interface Even {
	String message() default "Is not an even value.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
