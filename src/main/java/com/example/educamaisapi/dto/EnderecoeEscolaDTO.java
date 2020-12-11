package com.example.educamaisapi.dto;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Embeddable
public class EnderecoeEscolaDTO {

	private String NomeEscola;
	private String logradouro;
	private String tel;
	private String cep;
	private String email;

}
