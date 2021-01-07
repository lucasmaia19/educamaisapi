package com.example.educamaisapi.dto;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.springframework.web.multipart.MultipartFile;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AtividadeDTO {

	private String nome;

	private String enunciado;

	private MultipartFile arquivo;
	
	@OneToOne
	private FaixaEtaria faixaEtaria;

	@OneToOne
	private CampoExperiencia campoExperiencia;
	
	@OneToOne
	private AprendizagemDesenvolvimento aprendizagemDesenvolvimento;

}
