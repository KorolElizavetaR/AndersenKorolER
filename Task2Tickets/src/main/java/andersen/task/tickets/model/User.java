package andersen.task.tickets.model;

import java.util.HashSet;
import java.util.Set;

import andersen.task.tickets.model.enumeration.Roles;
import andersen.task.tickets.util.Indexable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class User extends Indexable {
	@Getter
	protected Roles role;
	private static final String PHONE_NUMBER_PATTERN = "\\+375\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}";
	@Pattern(regexp = PHONE_NUMBER_PATTERN)
	@Setter
	@Getter
	private String phone;
	@Email
	@Getter
	@Setter
	private String email;
	@Getter
	private Set<Ticket> tickets = new HashSet<>();
	
	public User() {
		this.role = Roles.ROLE_ADMIN;
	}
	
	public User(String phone, String email) {
		this();
		this.phone = phone;
		this.email = email;
	}
	
	public User(String phone, String email, Roles role) {
		this(phone, email);
		this.role = role;
	}
}
