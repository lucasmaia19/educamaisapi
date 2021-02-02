package com.example.educamaisapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name="faixaEtaria")
@Getter @Setter @ToString
public class FaixaEtaria implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String codigo;

	private String descricao;
 
	@JsonIgnore
	@ManyToMany(mappedBy="faixaEtariaList")
	private List<Atividade> atividades = new ArrayList<>();
	
//	@JsonIgnore
//	@ManyToMany(mappedBy="faixaEtariaListAd")
//	private List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentos = new ArrayList<>();
	
//	@JsonIgnore
//	@ManyToOne
//	private AprendizagemDesenvolvimento aprendizagemDesenvolvimento;

}
