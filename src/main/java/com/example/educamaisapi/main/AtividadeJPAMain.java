package com.example.educamaisapi.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
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
		atividade.setEnunciado("Atividade usado para estimular o tato 3...");

		List<FaixaEtaria> faixaEtariaList = new ArrayList<>();

		faixaEtariaSaved = faixaEtariaRepository.findById(2l).get();
		faixaEtariaList.add(faixaEtariaSaved);

		faixaEtariaSaved = faixaEtariaRepository.findById(3l).get();
		faixaEtariaList.add(faixaEtariaSaved);

		atividade.setFaixaEtariaList(faixaEtariaList);

		atividadeRepository.save(atividade);
	}

	@Test
	void criarObjetoTest2() {
		
		Atividade atividade1 = new Atividade();
		atividade1.setNome("Labiritnho do Fauno 2");
		atividade1.setEnunciado("Atividade usado para estimular o tato 2...");
		
		Atividade atividade2 = new Atividade();
		atividade2.setNome("Colorir");
		atividade2.setEnunciado("Jogue com a monica");
		
		FaixaEtaria faixaEtaria1 = new FaixaEtaria();
		faixaEtaria1.setCodigo("CGD2344");
		faixaEtaria1.setDescricao("Crianças de 1 a 3 anos");
		
		FaixaEtaria faixaEtaria2 = new FaixaEtaria();
		faixaEtaria2.setCodigo("CGD1111");
		faixaEtaria2.setDescricao("Crianças PEQUENAS de 3 a 6 anos");
		
		
		atividade1.getFaixaEtariaList().addAll(Arrays.asList(faixaEtaria1, faixaEtaria2));
		atividade2.getFaixaEtariaList().addAll(Arrays.asList(faixaEtaria1));

		faixaEtaria1.getAtividades().addAll(Arrays.asList(atividade1));
		faixaEtaria1.getAtividades().addAll(Arrays.asList(atividade1, atividade2));
		
		atividadeRepository.saveAll(Arrays.asList(atividade1, atividade2));
		faixaEtariaRepository.saveAll(Arrays.asList(faixaEtaria1, faixaEtaria2));
	}

	@Test
	void listarObjetoComDuasFaixaEtariaTest() {

		Optional<Atividade> atividadeOptional = atividadeRepository.findById(37l);
//		log.info(atividadeOptional.toString());
//		log.info(atividadeOptional.get().getFaixaEtariaList().size());

		assertEquals(2, atividadeOptional.get().getFaixaEtariaList().size());
	}

}
