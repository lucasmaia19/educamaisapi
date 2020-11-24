package com.example.educamaisapi.exemplos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.repository.AtividadeRepository;

@SpringBootTest
public class BinarioJpaMain {

	@Autowired
	AtividadeRepository repository;

//	public static void main(String[] args) {

//		Atividade exercicioLabirinto = new Atividade(1l, "Maternal", "Labirinto do Coelho", null);
//		System.out.println(exercicioLabirinto.getId());
//		System.out.println(exercicioLabirinto.getCategoria());
//		System.out.println(exercicioLabirinto.getNome());
//		System.out.println(exercicioLabirinto.getAtividade());
		
//		EntityManagerFactory emf = Persistence.creat
		
//	}

	@Test
	void conexaoMysqlTest() {
			
//		Atividade exercicioLabirinto = new Atividade(1l, "Maternal", "Labirinto do Coelho", null);
//		repository.save(exercicioLabirinto);
	}

}
