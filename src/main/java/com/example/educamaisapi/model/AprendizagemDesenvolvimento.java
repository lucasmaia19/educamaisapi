package com.example.educamaisapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AprendizagemDesenvolvimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private FaixaEtaria faixaEtaria;

	@OneToOne(cascade = CascadeType.ALL)
	private CampoExperiencia campoExperiencia;
	
	@JsonIgnore
	@ManyToMany(mappedBy="AprendizagemDesenvolvimentoList")
	private List<Atividade> atividades = new ArrayList<>();

//	@ManyToMany(cascade = CascadeType.ALL)
//	private List<FaixaEtaria> faixaEtariaListAd = new ArrayList<>();
//
//	@ManyToMany(cascade = CascadeType.ALL)
//	private List<CampoExperiencia> campoExperienciaListAprenDesen = new ArrayList<>();
	
//	@OneToMany(mappedBy="aprendizagemDesenvolvimento")
//	private List<FaixaEtaria> faixaEtarias = new ArrayList<>();

	private String codigo;

	private String descricao;

}
