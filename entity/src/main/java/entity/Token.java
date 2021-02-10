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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TOKEN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {
	
	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name = "TOKEN")
	private String token;

	@NotNull
	@NotEmpty
	@Column(name = "LOGIN")
	private String login;
	
	
	@NotNull
	@NotEmpty
	@Column(name = "EXPIRATION")
	private Date expiration;
	
	@NotNull
	@NotEmpty
	@Column(name = "ADMIN")
	private Boolean admin;
}