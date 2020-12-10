package com.example.educamaisapi.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Embeddable
public class EnderecoEscola {

	private String nomeEscola;
	private String logradouro;
	private String tel;
	private String cep;
	private String email;

}
