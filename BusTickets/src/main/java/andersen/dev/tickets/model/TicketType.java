package andersen.dev.tickets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum TicketType {
	DAY("DAY"), MONTH("MONTH"), YEAR("YEAR"), PRIME("PRIME");
	@Getter
	@Setter
	private String type;
}
