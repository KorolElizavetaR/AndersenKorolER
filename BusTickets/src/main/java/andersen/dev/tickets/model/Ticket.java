package andersen.dev.tickets.model;

import java.time.LocalDateTime;
import java.util.List;

import andersen.dev.tickets.config.Indexable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors (chain = true)
@EqualsAndHashCode (callSuper = true)
@ToString
public class Ticket extends Indexable {
	@Getter
	@Setter
	@NotNull
	private TicketType ticketType;
	@Getter
	@Setter
	private LocalDateTime creationDate;
	
	@ToString.Exclude
	@Setter
	@Getter
	private User user;
	
	public Ticket() {
		generateID();
		creationDate = LocalDateTime.now();
		ticketType = TicketType.DAY;
	}
	
	public Ticket(TicketType ticketType){
		this();
		this.ticketType = ticketType;
	}
}
