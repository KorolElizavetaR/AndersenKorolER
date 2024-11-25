package andersen.dev.tickets.config;

import com.mifmif.common.regex.Generex;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Accessors (chain = true)
public abstract class Indexable {
	static final Generex ID_GENERATOR = new Generex("[a-zA-Z1-9]{4}");
	@Setter
	@Getter
	protected String id;

	public void generateID() {
		this.id = ID_GENERATOR.random();
	}
}