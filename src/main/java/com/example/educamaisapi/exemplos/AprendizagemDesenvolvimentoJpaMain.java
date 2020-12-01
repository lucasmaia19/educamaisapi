package com.example.educamaisapi.exemplos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;

@SpringBootTest
public class AprendizagemDesenvolvimentoJpaMain {

	@Autowired
	AprendizagemDesenvolvimento repository;

	@Test
	void listarTeste() {
			
//		Atividade exercicioLabirinto = new Atividade(1l, "Maternal", "Labirinto do Coelho", null);
//		repository.save(exercicioLabirinto);
	}

}
