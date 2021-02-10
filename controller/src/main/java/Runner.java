import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import entity.Country;
import entity.User;
import repository.CountryRepository;
import repository.TokenRepository;
import repository.UserService;

@ComponentScan("br.com.mngs.test")
@EnableJpaRepositories(basePackageClasses = {UserService.class, CountryRepository.class, TokenRepository.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Runner implements CommandLineRunner {

	@Autowired
	private UserService user;

	@Autowired
	private CountryRepository country;

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user2 = new User(null, "convidado", "56379ff47fa2905160bc68e876531665ebfd3035dc1d23a4df256fda65791f04",
				"Usuário convidado", false);
		User user3 = new User(null, "admin", "ea5cae9eb7adce86252fac32462523d0b55f3ddc6282e941518fc9ee05c538c8",
				"Gestor", true);
		
		user.save(user2);
		user.save(user3);
		
		Country c1 = new Country(null, "Brasil", "BR", "Brasileiro");
		Country c2 = new Country(null, "Argentina", "AR", "Argentino");
		Country c3 = new Country(null, "Alemanha", "AL", "Alemão");
		
		country.save(c1);
		country.save(c2);
		country.save(c3);
	}

}
