package controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioAutenticado {
	
	private String login;
	private String nome;
	private String token;
	private boolean admin;
	private boolean authenticated;
}
