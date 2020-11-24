package com.example.educamaisapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter 
@NoArgsConstructor 
@AllArgsConstructor
@Entity
public class Atividade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String categoria;
	
	private String nome;
	
	private String nomeArquivo;
	
	private String extensao;
	
	@Lob
	private byte[] atividade;

	public Atividade (byte[] atividade) {
		this.atividade = atividade;
	}

	public Atividade (String nomeArquivo, byte[] atividade) {
		this.atividade = atividade;
		this.nomeArquivo = nomeArquivo;
	}

}
