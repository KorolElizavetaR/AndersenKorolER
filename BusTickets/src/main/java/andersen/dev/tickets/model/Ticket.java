package andersen.dev.tickets.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer ticketId;

	@NotNull
	@Column(name = "type")
	@JdbcType(PostgreSQLEnumJdbcType.class)
	private TicketType ticketType;

	@Column(name = "ticket_creation_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime creationDate;

	@ToString.Exclude
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Ticket() {
		ticketType = TicketType.DAY;
	}

	public Ticket(TicketType ticketType) {
		this();
		this.ticketType = ticketType;
	}
}
