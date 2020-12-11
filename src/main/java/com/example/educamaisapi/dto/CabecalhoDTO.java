package com.example.educamaisapi.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CabecalhoDTO {

	private String professora;

	private String turma;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;

//	@JsonFormat(pattern="yyyy-MM-dd")
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate dataProva;

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
