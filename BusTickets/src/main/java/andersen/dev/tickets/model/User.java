package andersen.dev.tickets.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer userId;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime creationDate;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Ticket> tickets;

	public User(String name) {
		this.name = name;
	}
}
