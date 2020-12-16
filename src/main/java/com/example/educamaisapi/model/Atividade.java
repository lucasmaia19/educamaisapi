package com.example.educamaisapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Atividade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
