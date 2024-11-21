package andersen.task.tickets.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import andersen.task.tickets.model.enumeration.SectorHall;
import andersen.task.tickets.util.Indexable;
import andersen.task.tickets.util.Printable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class Ticket extends Indexable implements Printable {
	private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	@Getter
	private static final BigDecimal MAX_BACKPACK_WEIGHT = new BigDecimal("20.250");
	@Getter
	@Size(max = 10)
	private String consertHall;
	@Pattern (regexp = "\\d{3}")
	@Getter
	private String eventCode;
	private final LocalDateTime createdAt;
	@Setter
	private LocalDateTime startsAt;
	@Getter
	private boolean isPromo;
	@Getter
	@Setter
	private SectorHall stadiumSector;
	@Getter
	@Setter
	@Positive
	private BigDecimal price;
	@Getter
	private boolean isExpired;

	public Ticket() {
		this.createdAt = LocalDateTime.now();
	}

	public Ticket(String consertHall, String eventCode, LocalDateTime startsAt) {
		this();
		this.consertHall = consertHall;
		this.eventCode = eventCode;
		this.startsAt = startsAt;
		this.isExpired = !this.startsAt.isAfter(LocalDateTime.now());
	}

	public Ticket(String consertHall, String eventCode, LocalDateTime startsAt, boolean isPromo, SectorHall stadiumSector,
			double price) {
		this(consertHall, eventCode, startsAt);
		this.isPromo = isPromo;
		this.stadiumSector = stadiumSector;
		this.price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
	}

	public String getCreatedAt() {
		return DATETIME_FORMAT.format(createdAt);
	}

	public String getStartsAt() {
		return DATETIME_FORMAT.format(startsAt);
	}

	@Override
	public String toString() {
		return "Ticket [ticketID=" + getID() + ", consertHall=" + consertHall + ", eventCode=" + eventCode
				+ ", createdAt=" + getCreatedAt() + ", startsAt=" + getStartsAt() + ", isPromo=" + isPromo
				+ ", stadiumSector=" + stadiumSector + ", price=" + price + ", MAX_BACKPACK_WEIGHT = "
				+ MAX_BACKPACK_WEIGHT + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(consertHall, createdAt, eventCode, isPromo, price, stadiumSector, startsAt, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(consertHall, other.consertHall) && Objects.equals(createdAt, other.createdAt)
				&& eventCode == other.eventCode && isPromo == other.isPromo && Objects.equals(price, other.price)
				&& stadiumSector == other.stadiumSector && Objects.equals(startsAt, other.startsAt)
				&& Objects.equals(id, other.id);
	}

//	public String shared(String phone) {
//		return this + " is shared via phone number:" + phone;
//	}
//
//	public String shared(String phone, String email) {
//		return shared(phone) + "\nand email:email";
//	}
}
