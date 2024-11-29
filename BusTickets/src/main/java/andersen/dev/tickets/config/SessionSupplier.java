package andersen.dev.tickets.config;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.hibernate.cfg.Configuration;

import andersen.dev.tickets.model.Ticket;
import andersen.dev.tickets.model.User;
import lombok.Getter;

@Component
public class SessionSupplier {
	@Getter
	private final SessionFactory sessionFactory;

	public SessionSupplier() {
		sessionFactory = new Configuration().addAnnotatedClass(Ticket.class).addAnnotatedClass(User.class)
				.buildSessionFactory();
	}
}
