package andersen.task.tickets.model;

import com.mifmif.common.regex.Generex;

public abstract class Indexable {
	static final Generex TICKET_ID_GENERATOR = new Generex("[a-zA-Z1-9]{1,4}");
	final protected String id = TICKET_ID_GENERATOR.random();

	public String getID() {
		return id;
	}

}
