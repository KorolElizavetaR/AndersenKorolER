//package andersen.dev.tickets.config;
//
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.hibernate.cfg.Configuration;
//
//import andersen.dev.tickets.model.Ticket;
//import andersen.dev.tickets.model.User;
//import lombok.Getter;
//
////@Component
////@EnableTransactionManagement
////public class SessionSupplier {
////	@Getter
////	private final SessionFactory sessionFactory;
////
////	public SessionFactory sessionFactory() {
////		return new Configuration().addAnnotatedClass(Ticket.class).addAnnotatedClass(User.class)
////				.buildSessionFactory();
////	}
////}
