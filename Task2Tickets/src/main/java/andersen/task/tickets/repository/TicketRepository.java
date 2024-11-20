package andersen.task.tickets.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.model.ticket.SectorHall;
import lombok.Getter;

public class TicketRepository {
	@Getter
	List<Ticket> tickets = new ArrayList<>();

	public TicketRepository() {
		tickets = new ArrayList<>(Arrays.asList(
				new Ticket("TEST1", 221, LocalDateTime.of(2024, 11, 10, 19, 30), false, SectorHall.A, 34.05),
				new Ticket("TEST1", 221, LocalDateTime.of(2024, 11, 20, 9, 30), false, SectorHall.B, 65.953525)));
	}
	
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public Ticket getTicket(String id) throws InstanceNotFoundException{
		return tickets.stream().filter(ticket -> ticket.getID().equals(id)).findFirst()
				.orElseThrow(() -> new InstanceNotFoundException());
	}
}
