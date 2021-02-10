package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public interface UserService extends JpaRepository<User, Long> {

	
	@Query("SELECT u FROM User u WHERE u.login = ?1 and u.hash = ?2")
	Optional<User> findBy(String login, String hash);
}
