package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entity.Country;

@Repository
public interface CountryRepository  extends JpaRepository<Country, Long> {
	
	@Query("SELECT c FROM Country c WHERE c.name = ?1")
	List<Country> findByName(String name);
}
