package andersen.task.tickets.service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.ContentPrintering;
import andersen.task.tickets.model.ticket.SectorHall;
import andersen.task.tickets.model.ticket.Ticket;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public class TicketService implements ContentPrintering {
	List<Ticket> tickets = new ArrayList<>();

	public TicketService() {
		tickets = new ArrayList<>(Arrays.asList(
				new Ticket("TEST1", 221, LocalDateTime.of(2024, 11, 10, 19, 30), false, SectorHall.A, 34.05),
				new Ticket("TEST1", 221, LocalDateTime.of(2024, 11, 20, 9, 30), false, SectorHall.B, 65.953525)));
	}

	public Ticket addTicket(Ticket ticket) {
		try {
			Set<ConstraintViolation<Ticket>> violations = Validation.buildDefaultValidatorFactory().getValidator()
					.validate(ticket);
			if (!violations.isEmpty())
				throw new ConstraintViolationException(violations);
			tickets.add(ticket);
		} catch (ConstraintViolationException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return ticket;
	}

	public List<Ticket> getAllTickets() {
		return tickets;
	}

	public Ticket getTicketByID(String id) throws InstanceNotFoundException {
		return tickets.stream().filter(ticket -> ticket.getID().equals(id)).findFirst()
				.orElseThrow(() -> new InstanceNotFoundException());
	}

	@Override
	public void printer() {
		StringBuilder parameters = new StringBuilder();
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method : methods) {
			System.out.print(method.getReturnType().getSimpleName() + " " + method.getName() + "(");

			Class<?>[] parameterTypes = method.getParameterTypes();
			if (parameterTypes.length == 0)
				continue;
			for (Class<?> params : parameterTypes) {
				parameters.append(params.getSimpleName() + ", ");
			}
			parameters.setLength(parameters.length() - 2);
			System.out.println(")");
		}
	}
}
