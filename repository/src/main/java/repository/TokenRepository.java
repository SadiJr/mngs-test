package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entity.Token;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	
	@Query("SELECT t FROM Token t WHERE t.token = ?1")
	Optional<Token> findByToken(String token);
	
	
}
