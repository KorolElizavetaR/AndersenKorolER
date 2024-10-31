package andersen.task.tickets.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public class TicketService {
	List<Ticket> tickets;
	
	public TicketService()
	{
		tickets = new ArrayList<>(Arrays.asList(
				new Ticket("4 Hall", 345, new Calendar.Builder().setDate(2025, 0, 23).setTimeOfDay(19, 00, 0).build()),
				new Ticket("Main hall", 231, new Calendar.Builder().setDate(2024, 4, 29).setTimeOfDay(15, 00, 0).build(), false, 'A', 45.6056374),
				new Ticket("AAAA", 231, new Calendar.Builder().setDate(2025, 1, 1).setTimeOfDay(13, 00, 0).build(), false, 'C', 47.34),
				new Ticket("bbbb", 865, new Calendar.Builder().setDate(2025, 1, 1).setTimeOfDay(13, 00, 0).build(), false, 'C', 45.78),
				new Ticket("CCC", 345, new Calendar.Builder().setDate(2025, 1, 1).setTimeOfDay(13, 00, 0).build(), false, 'C', 47.34),
				new Ticket("CCC", 423, new Calendar.Builder().setDate(2025, 1, 1).setTimeOfDay(13, 00, 0).build(), false, 'C', 47.34),
				new Ticket("CCC", 345, new Calendar.Builder().setDate(2027, 1, 1).setTimeOfDay(13, 00, 0).build(), false, 'C', 234.53),
				new Ticket("CCC", 345, new Calendar.Builder().setDate(2025, 1, 1).setTimeOfDay(13, 00, 0).build(), false, 'C', 14.34214),
				new Ticket("CCC", 352, new Calendar.Builder().setDate(2024, 11, 11).setTimeOfDay(13, 00, 0).build(), false, 'C', 47.34),
				new Ticket("CCC", 526, new Calendar.Builder().setDate(2025, 1, 1).setTimeOfDay(13, 00, 0).build(), false, 'C', 47.34)));
	}
	
	public Ticket addTicket(Ticket ticket)
	{
		try
		{
			Set<ConstraintViolation<Ticket>> violations =  Validation.buildDefaultValidatorFactory().getValidator().validate(ticket);
	        if (!violations.isEmpty()) 
	        	throw new ConstraintViolationException(violations);
			tickets.add(ticket);
			System.out.println("Ticket is succesfully added!");
		}
		catch(ConstraintViolationException ex)
		{
			System.out.println(ex.getLocalizedMessage());
		}
		return ticket;
	}
	
	public List<Ticket> getAllTickets()
	{
		return tickets;
	}
	
	public Ticket getTicketByID(String id) throws InstanceNotFoundException
	{
		return tickets.stream().filter(ticket -> ticket.getTicketID().equals(id)).findFirst().orElseThrow(()-> new InstanceNotFoundException());
	}
}
