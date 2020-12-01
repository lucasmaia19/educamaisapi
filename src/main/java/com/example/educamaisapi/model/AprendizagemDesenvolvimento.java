package com.example.educamaisapi.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AprendizagemDesenvolvimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private FaixaEtaria faixaEtaria;

	@OneToOne(cascade = CascadeType.ALL)
	private CampoExperiencia campoExperiencia;

	private String codigo;

	private String descricao;

}
