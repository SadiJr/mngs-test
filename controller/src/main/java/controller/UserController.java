package controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDto;
import entity.Token;
import entity.User;
import repository.TokenRepository;
import repository.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	private UserService user;

	@Autowired
	private TokenRepository token;

	@PostMapping("/autenticar")
	public ResponseEntity<UsuarioAutenticado> authenticate(@Valid @RequestBody UserDto dto) {
		try {

			Optional<User> u = user.findBy(dto.getLogin(), new String(getSHA(dto.getPassword())));
			if (u.isEmpty()) {
				User us = u.get();
				String randomToken = UUID.randomUUID().toString();

				Date targetTime = new Date();
				targetTime = DateUtils.addMinutes(targetTime, 5);

				token.save(new Token(null, randomToken, us.getLogin(), targetTime, us.getAdmin().booleanValue()));
				return new ResponseEntity<>(new UsuarioAutenticado(us.getLogin(), us.getName(), randomToken,
						us.getAdmin().booleanValue(), true), HttpStatus.OK);

			}
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/renovar-ticket/{ticket}")
	public ResponseEntity<Boolean> renovar(String ticket) {
		if (ticket.isBlank()) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}

		Optional<Token> t = token.findByToken(ticket);
		if (t.isPresent()) {
			Token tic = t.get();
			Date expiration = tic.getExpiration();

			expiration = DateUtils.addMinutes(expiration, 5);
			tic.setExpiration(expiration);
			token.save(tic);
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}

	private byte[] getSHA(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

}
