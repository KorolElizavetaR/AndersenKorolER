package andersen.dev.tickets.model;

import java.time.LocalDate;

import andersen.dev.tickets.constraint.Even;
import andersen.dev.tickets.constraint.ValidDateForTicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Component
//@Scope (scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ValidDateForTicketType
public class Ticket {
	@NotBlank
	@Pattern (regexp = "[A-Z]{3}")
	private String ticketName;
	@NotNull
	private TicketType ticketType;
	@PastOrPresent
	private LocalDate startDate;
	@Positive
	@Even
	private int price; 
}
