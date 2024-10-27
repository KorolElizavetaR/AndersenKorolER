package andersen.task.tickets.model;

import com.mifmif.common.regex.Generex;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class Ticket {
	private static final Generex TICKET_ID_GENERATOR = new Generex("[a-zA-Z1-9]{1,4}");

	/**
	 * Because of regex, this field's length is always between 1 and 4
	 */
	@Pattern(regexp = "[a-zA-Z1-9]{1,4}")
	private final char[] ticketID;
	@Setter
	private char[] consertHall;
	@Min (value = 100)
	@Max (value = 999)
	@Setter
	@Getter
	private int eventCode;
	
	public Ticket() {
		char[] generatedId = TICKET_ID_GENERATOR.random().toCharArray();
		ticketID = new char[generatedId.length];
		for (int i = 0; i < generatedId.length; i++) {
			this.ticketID[i] = generatedId[i];
		}
	}
	
	public Ticket(char[] consertHall, int eventCode) {
		this();
		if (consertHall.length > 10)
		{
			//probably not the best choice of exception
			throw new IllegalStateException();
		}
		this.consertHall = consertHall;
		this.eventCode = eventCode;
	}
	
	public String getTicketID()
	{
		return String.copyValueOf(ticketID);
	}
	
	public String getConsertHall()
	{
		return String.copyValueOf(consertHall);
	}
}
