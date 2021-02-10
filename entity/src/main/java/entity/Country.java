package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name = "NAME", unique = true)
	private String name;
	
	@NotNull
	@NotEmpty
	@Column(name = "SIGLA", unique = true)
	private String sigla;
	
	@NotNull
	@NotEmpty
	@Column(name = "GENTILICO", unique = true)
	private String gentilico;
}
