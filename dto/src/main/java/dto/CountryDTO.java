package dto;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
	private String name;
	private String sigla;
	private String gentilico;
}
