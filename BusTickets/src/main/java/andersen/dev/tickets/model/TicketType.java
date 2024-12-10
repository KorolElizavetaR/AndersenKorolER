package andersen.dev.tickets.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TicketType {
	DAY("DAY"), WEEK("WEEK"), MONTH("MONTH"), YEAR("YEAR");
	String value;
}
