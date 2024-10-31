package andersen.task.tickets;

import java.util.Calendar;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.service.TicketService;

public class MainClass {	
	
	public static void main(String[] args) {
		TicketService service = new TicketService();
		service.addTicket(new Ticket("5 Hall", 345, new Calendar.Builder()
	            .setDate(2024, 11, 30).setTimeOfDay(19, 00, 0).build()));
		
		Ticket t1 = service.addTicket(new Ticket("Main hall", 231, new Calendar.Builder()
	            .setDate(2024, 9, 29).setTimeOfDay(19, 00, 0).build(), false, 'A', 45.6056374)); //Soon will have ConstraintViolation
		
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
			System.out.printf("Ticket with ID %d is not found", t1.getTicketID());
		}
	}
	
	
}
