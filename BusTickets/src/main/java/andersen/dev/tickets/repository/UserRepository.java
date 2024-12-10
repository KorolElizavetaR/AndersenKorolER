package andersen.dev.tickets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import andersen.dev.tickets.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.tickets WHERE u.userId = :id")
	Optional<User> findUserWithTicketsById(@Param("id") int id);
}
