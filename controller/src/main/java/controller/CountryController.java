package controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Country;
import entity.Token;
import repository.CountryRepository;
import repository.TokenRepository;

@RestController
@RequestMapping("/pais")
public class CountryController {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private CountryRepository repository;
	
	@GetMapping("/listar") 
	public List<Country> list(){
		return repository.findAll();
	}
	
	@PostMapping("/salvar/{token}")
	public ResponseEntity<Country> save(String token, @Valid @RequestBody Country dto) {
		Optional<Token> t = tokenRepository.findByToken(token);
		Date current = new Date();
		
		if (token.isBlank() || t.isEmpty() || t.get().getExpiration().after(current)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (dto.getId().equals(0)) {
			dto.setId(null);
			return new ResponseEntity<> (repository.save(dto), HttpStatus.CREATED);
		}
		
		return new ResponseEntity<> (repository.save(dto), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/pesquisar/{name}")
	public ResponseEntity<List<Country>> findCountries(String name) {
		return new ResponseEntity<List<Country>>(repository.findByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/excluir/{token}/{id}")
	public ResponseEntity<Boolean> delete(String token, Long id) {
		Optional<Token> t = tokenRepository.findByToken(token);
		Date current = new Date();
		
		if (token.isBlank() || t.isEmpty() || !t.get().getAdmin().booleanValue() || t.get().getExpiration().after(current)) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		
		repository.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
		
	}
}
