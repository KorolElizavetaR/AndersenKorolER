//package andersen.dev.tickets.repository;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.springframework.stereotype.Repository;
//
//import andersen.dev.tickets.config.SessionSupplier;
//import andersen.dev.tickets.model.User;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.JoinType;
//import jakarta.persistence.criteria.Root;
//import lombok.RequiredArgsConstructor;
//
//@Repository
//@RequiredArgsConstructor
//public class UserRepository {
//	private final SessionSupplier supplier;
//
//	public boolean deleteUser(int id) {
//		boolean succesfulRemoval = false;
//		Session session = supplier.getSessionFactory().getCurrentSession();
//		User user = session.get(User.class, id);
//		if (user != null) {
//			session.remove(user);
//			succesfulRemoval = true;
//		}
//		session.close();
//		return succesfulRemoval;
//	}
//
//	public Set<User> getUserByIdWithTickets(int id) {
//		Session session = supplier.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		CriteriaBuilder cb = session.getCriteriaBuilder();
//		CriteriaQuery<User> cq = cb.createQuery(User.class);
//		Root<User> userRoot = cq.from(User.class);
//		userRoot.fetch("tickets", JoinType.LEFT);
//		cq.select(userRoot).where(cb.equal(userRoot.get("id"), id));
//		Set<User> users = new HashSet<>(session.createQuery(cq).getResultList());
//		transaction.commit();
//		session.close();
//		return users;
//	}
//
//	public User getUserByIdWithoutTickets(int id) {
//		Session session = supplier.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		User user = session.get(User.class, id);
//		transaction.commit();
//		session.close();
//		return user;
//	}
//
//	public User addUser(User user) {
//		Session session = supplier.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		session.persist(user);
//		transaction.commit();
//		session.close();
//		return user;
//	}
//
//}
