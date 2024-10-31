package andersen.task.tickets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public class TicketService {
	List<Ticket> tickets = new ArrayList<>();
	
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
