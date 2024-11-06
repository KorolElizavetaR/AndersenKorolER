package andersen.dev.tickets.model;

import lombok.Getter;
import lombok.Setter;

public enum TicketType {
	DAY("DAY"), MONTH("MONTH"), YEAR("YEAR"), PRIME("PRIME");
	
	String type;
	
	TicketType(String type) {
		this.type = type;
	}

}
