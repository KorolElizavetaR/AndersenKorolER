package andersen.task.tickets.model;

import java.util.Arrays;

import com.mifmif.common.regex.Generex;

public interface Indexable {
	static final Generex TICKET_ID_GENERATOR = new Generex("[a-zA-Z1-9]{1,4}");
	final String id = TICKET_ID_GENERATOR.random();

	default String getID() {
		return id;
	}

}
