package andersen.dev.tickets.model;

import java.time.LocalDate;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Scope (scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Ticket {
	@NotBlank
	@Pattern (regexp = "[A-Z]{3}")
	private String ticketName;
	private TicketType ticketType;
	@PastOrPresent
	private LocalDate startDate;
	@Positive
	private int price; // The concepts of “even” and “odd” only apply to integers, not to floating point numbers
}
