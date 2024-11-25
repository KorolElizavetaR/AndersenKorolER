package andersen.dev.tickets.model;

import java.time.LocalDate;

import andersen.dev.tickets.config.Indexable;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidDateForTicketType
@EqualsAndHashCode (callSuper = true)
public class Ticket extends Indexable {
	@NotBlank
	@Pattern (regexp = "[A-Z]{3}")
	@Getter
	@Setter
	private String ticketName;
	@NotNull
	private TicketType ticketType;
}
