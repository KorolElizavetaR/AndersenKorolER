package andersen.dev.tickets.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import andersen.dev.tickets.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public void deleteUser(int id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}

	public Set<User> getUserByIdWithTickets(int id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> userRoot = cq.from(User.class);
		userRoot.fetch("tickets", JoinType.LEFT);
		cq.select(userRoot).where(cb.equal(userRoot.get("id"), id));
		Set<User> users = new HashSet<>(entityManager.createQuery(cq).getResultList());
		return users;
	}

	public User getUserByIdWithoutTickets(int id) {
		return entityManager.find(User.class, id);
	}

	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

}
