package andersen.dev.tickets.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@ToString
@Accessors (chain = true)
public class User {
	@Getter
	@Setter
	private Integer userId;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private LocalDateTime creationDate;

	public User() {
		creationDate = LocalDateTime.now();
	}

	public User(String name) {
		this.name = name;
	}
}
