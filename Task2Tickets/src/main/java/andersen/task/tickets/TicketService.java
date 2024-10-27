package andersen.task.tickets;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class TicketService {	
	List<Ticket> tickets = new ArrayList<>();
	
	public static void main(String[] args) {
		TicketService service = new TicketService();
		service.addTicket(new Ticket("5 Hall", 345, new Calendar.Builder()
	            .setDate(2024, 11, 30).setTimeOfDay(19, 00, 0).build()));
		
		Ticket t1 = service.addTicket(new Ticket("Main hall", 231, new Calendar.Builder()
	            .setDate(2024, 10, 29).setTimeOfDay(19, 00, 0).build(), false, 'A', 45.6056374)); //Soon will have ConstraintViolation
		
		service.addTicket(new Ticket("55555555555555555", 345, new Calendar.Builder()
	            .setDate(2024, 11, 30).setTimeOfDay(19, 00, 0).build())); // ConstraintViolation
		
		service.addTicket(new Ticket("5 Hall", 345, new Calendar.Builder()
	            .setDate(2020, 11, 30).setTimeOfDay(19, 00, 0).build())); // ConstraintViolation
		
		service.addTicket(new Ticket("Main hall", 231, new Calendar.Builder()
	            .setDate(2024, 10, 29).setTimeOfDay(19, 00, 0).build(), false, 'g', 45.6056374)); // ConstraintViolation
		
		service.getAllTickets().stream().forEach(System.out::println);
		
		try {
			System.out.println(service.getTicketByID(t1.getTicketID()));
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
