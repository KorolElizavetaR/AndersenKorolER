package andersen.task.tickets.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.ContentPrinter;
import andersen.task.tickets.model.SectorHall;
import andersen.task.tickets.model.Ticket;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public class TicketService implements ContentPrinter {
	List<Ticket> tickets = new ArrayList<>();

	public TicketService() {
		tickets = new ArrayList<>(Arrays.asList(
				new Ticket("5 Hall", 345, new Calendar.Builder().setDate(2024, 11, 30).setTimeOfDay(19, 00, 0).build()),
				new Ticket("AAA", 231, new Calendar.Builder().setDate(2024, 10, 29).setTimeOfDay(19, 00, 0).build(),
						false, SectorHall.A, 45.6056374),
				new Ticket("BBB", 231, new Calendar.Builder().setDate(2025, 1, 20).setTimeOfDay(13, 00, 0).build(),
						false, SectorHall.A, 55.55)));
	}

	public Ticket addTicket(Ticket ticket) {
		try {
			Set<ConstraintViolation<Ticket>> violations = Validation.buildDefaultValidatorFactory().getValidator()
					.validate(ticket);
			if (!violations.isEmpty())
				throw new ConstraintViolationException(violations);
			tickets.add(ticket);
			System.out.println("Ticket is succesfully added!");
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
