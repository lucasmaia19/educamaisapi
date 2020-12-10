package com.example.educamaisapi.dto;

import javax.persistence.Embedded;

import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CabecalhoDTO {

	private String professora;

	private String turma;

	private String data;

	private String aluno;

	private String NomeEscola;
	private String logradouro;
	private String tel;
	private String cep;
	private String email;

	
//	@Embedded
//	private EnderecoeEscolaDTO enderecoEscolaDTO;

	private MultipartFile logoPrefeitura;

	private MultipartFile logoEscola;

}
