package com.example.educamaisapi.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.repository.AtividadeRepository;
import com.example.educamaisapi.repository.FaixaEtariaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AtividadeJPAMain {

	@Autowired
	AtividadeRepository atividadeRepository;
	
	@Autowired
	FaixaEtariaRepository faixaEtariaRepository;

//	public static void main(String[] args) {

//		Atividade exercicioLabirinto = new Atividade(1l, "Maternal", "Labirinto do Coelho", null);
//		System.out.println(exercicioLabirinto.getId());
//		System.out.println(exercicioLabirinto.getCategoria());
//		System.out.println(exercicioLabirinto.getNome());
//		System.out.println(exercicioLabirinto.getAtividade());
		
//		EntityManagerFactory emf = Persistence.creat
		
//	}

	@Test
	void listarObjetoTest() {

		List<Atividade> atividadeList = atividadeRepository.findAll();

		for (Atividade atividade: atividadeList) {
			log.info("atividade: {}", atividade);
		}
	}
	
	@Test
	void criarObjetoTest() {

		FaixaEtaria faixaEtariaSaved;
		
		Atividade atividade = new Atividade();
		atividade.setNome("Labiritnho do Fauno 2");
		atividade.setEnunciado("Atividade usado para estimular o tato 2...");

		List<FaixaEtaria> faixaEtariaList = new ArrayList<>();

		faixaEtariaSaved = faixaEtariaRepository.findById(1l).get();
		faixaEtariaList.add(faixaEtariaSaved);

		faixaEtariaSaved = faixaEtariaRepository.findById(2l).get();
		faixaEtariaList.add(faixaEtariaSaved);

		atividade.setFaixaEtariaList(faixaEtariaList);

		atividadeRepository.save(atividade);
	}
	
	@Test
	void listarObjetoComDuasFaixaEtariaTest() {

		Atividade atividade = atividadeRepository.findById(14l).get();
		log.info(atividade.toString());
	}

}
