package andersen.dev.tickets.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import andersen.dev.tickets.constraint.annotation.Even;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer ticketId;

	@Column(name = "ticket_name", nullable = false)
	@NotNull
	@Pattern(regexp = "[A-Z]{3}")
	private String ticketName;

	@NotNull
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TicketType ticketType;

	@Column(name = "ticket_creation_date", updatable = false)
	@CreationTimestamp
	@PastOrPresent
	private LocalDate creationDate;

	@Column(name = "price", nullable = false)
	@Positive
	@Even
	private Integer price;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Ticket(@NotNull @Pattern(regexp = "[A-Z]{3}") String ticketName, @NotNull TicketType ticketType,
			LocalDate creationDate, @PositiveOrZero int price) {
		super();
		this.ticketName = ticketName;
		this.ticketType = ticketType;
		this.creationDate = creationDate;
		this.price = price;
	}

}
