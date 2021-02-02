package com.example.educamaisapi.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	private List<FaixaEtaria> faixaEtariaList = new ArrayList<>();
	
	@ManyToMany
	private List<CampoExperiencia> campoExperienciaList = new ArrayList<>();
	
//	@OneToOne
//	private AprendizagemDesenvolvimento aprendizagemDesenvolvimento;
	
	@ManyToMany
	private List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList = new ArrayList<>();

}
