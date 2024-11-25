package andersen.dev.tickets.model;

import java.time.LocalDateTime;

import andersen.dev.tickets.config.Indexable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode (callSuper = true)
public class Ticket extends Indexable {
	@Getter
	@Setter
	@NotNull
	private TicketType ticketType;
	@Getter
	@Setter
	private LocalDateTime creationDate;
	
	Ticket() {
		generateID();
		creationDate = LocalDateTime.now();
		ticketType = TicketType.DAY;
	}
	
	public Ticket(TicketType ticketType){
		this();
		this.ticketType = ticketType;
	}
}
