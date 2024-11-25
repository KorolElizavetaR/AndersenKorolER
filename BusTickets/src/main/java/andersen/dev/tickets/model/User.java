package andersen.dev.tickets.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Getter
	@Setter
	private Integer user_id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private LocalDateTime creation_date;
	
	User(String name) {
		this.name = name;
	}
}
