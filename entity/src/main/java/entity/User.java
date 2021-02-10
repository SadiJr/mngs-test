package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "USER")
@Getter
@Setter
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name = "LOGIN", unique = true)
	private String login;

	@NotNull
	@NotEmpty
	@Column(name = "HASH")
	private String hash;
	
	
	@NotNull
	@NotEmpty
	@Column(name = "NAME")
	private String name;
	
	@NotNull
	@NotEmpty
	@Column(name = "ADMIN")
	private Boolean admin;

}
