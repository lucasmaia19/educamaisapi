package com.example.educamaisapi.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.example.educamaisapi.dto.EnderecoeEscolaDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Cabecalho implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String professora;
	
	private String turma;
	
	private String data;
	
	private String aluno;

	private String nomeEscola;
	private String logradouro;
	private String tel;
	private String cep;
	private String email;
	
//	@Embedded
//	private EnderecoEscola enderecoEscola;

	@Lob
	private byte[] logoPrefeitura;
	
	@Lob
	private byte[] logoEscola;

}
