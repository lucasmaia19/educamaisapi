package com.example.educamaisapi.dto;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany()
	private List<FaixaEtaria> faixaEtaria;

	@OneToOne
	private CampoExperiencia campoExperiencia;
	
	@OneToOne
	private AprendizagemDesenvolvimento aprendizagemDesenvolvimento;

}
