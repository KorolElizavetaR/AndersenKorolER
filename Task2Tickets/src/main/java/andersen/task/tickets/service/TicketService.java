package andersen.task.tickets.service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.model.ticket.SectorHall;
import andersen.task.tickets.repository.TicketRepository;
import andersen.task.tickets.util.Printable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TicketService implements Printable {
	private final TicketRepository repository;

	public Ticket addTicket(Ticket ticket) {
		try {
			Set<ConstraintViolation<Ticket>> violations = Validation.buildDefaultValidatorFactory().getValidator()
					.validate(ticket);
			if (!violations.isEmpty())
				throw new ConstraintViolationException(violations);
			repository.addTicket(ticket);
		} catch (ConstraintViolationException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return ticket;
	}

	public List<Ticket> getAllTickets() {
		return repository.getTickets();
	}

	public Ticket getTicketByID(String id) throws InstanceNotFoundException {
		return repository.getTicket(id);
	}

	@Override
	public void printMethods() {
		System.out.println("From TicketService");
		Printable.super.printMethods();;
	}
}
