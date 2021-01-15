package com.example.educamaisapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
public class Atividade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@OneToOne
//	private Cabecalho cabecalho;

	private String nome;

	private String enunciado;

	@Lob
	private byte[] arquivo;

	private String arquivoNome;

	private String arquivoExtensao;

	private Long arquivoTamanho = 0L;

	@OneToMany(fetch = FetchType.EAGER)
	private List<FaixaEtaria> faixaEtariaList = new ArrayList<>();

//	@OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL)
//	private List<FaixaEtaria> faixaEtariaList = new ArrayList<>();

//	@OneToOne
//	private CampoExperiencia campoExperiencia;

//	@OneToOne
//	private AprendizagemDesenvolvimento aprendizagemDesenvolvimento;

}
