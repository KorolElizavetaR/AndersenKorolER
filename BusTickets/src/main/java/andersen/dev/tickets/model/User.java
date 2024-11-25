package andersen.dev.tickets.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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

	User() {
		creationDate = LocalDateTime.now();
	}

	User(String name) {
		this.name = name;
	}
}
