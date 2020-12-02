package com.example.educamaisapi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.repository.AprendizagemDesenvolvimentoRepository;

@SpringBootTest
public class AprendizagemDesenvolvimentoJpa {

	@Autowired
	AprendizagemDesenvolvimentoRepository repository;

	@Test
	void listarCadastrarListarTeste() {

		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList;

		repository.deleteAll();

		
		FaixaEtaria faixaEtaria = new FaixaEtaria();
		faixaEtaria.setCodigo("EI01");
		faixaEtaria.setDescricao("Bebês (zero a 1 ano e 6 meses)");
		
		faixaEtaria.setCodigo("EI02");
		faixaEtaria.setDescricao("Crianças bem pequenas (1 ano e 7 meses a 3 anos e 11 meses)");
		
		faixaEtaria.setCodigo("EI03");
		faixaEtaria.setDescricao("Crianças pequenas (4 anos a 5 anos e 11 meses)");

		CampoExperiencia campoExperiencia = new CampoExperiencia();
		campoExperiencia.setCodigo("TS01");
		campoExperiencia.setDescricao("Traços, sons, cores e formas");

		campoExperiencia.setCodigo("TS02");
		campoExperiencia.setDescricao("O eu, o outro e o nós");

		campoExperiencia.setCodigo("TS03");
		campoExperiencia.setDescricao("Corpo, gestos e movimentos");

		campoExperiencia.setCodigo("TS04");
		campoExperiencia.setDescricao("Escuta, fala, pensamento e imaginação");

		campoExperiencia.setCodigo("TS05");
		campoExperiencia.setDescricao("Espaços, tempos, quantidades, relações e transformações");

		AprendizagemDesenvolvimento aprendizagemDesenvolvimento = new AprendizagemDesenvolvimento();
		aprendizagemDesenvolvimento.setFaixaEtaria(faixaEtaria);
		aprendizagemDesenvolvimento.setCampoExperiencia(campoExperiencia);

//		aprendizagemDesenvolvimento.getFaixaEtaria().setCodigo("EI01");
//		aprendizagemDesenvolvimento.getCampoExperiencia().setCodigo("TS01");
		

		aprendizagemDesenvolvimento.setCodigo("EI01TS01");
		aprendizagemDesenvolvimento.getFaixaEtaria().setCodigo("EI01");
		aprendizagemDesenvolvimento.getCampoExperiencia().setCodigo("TS01");
		aprendizagemDesenvolvimento.getFaixaEtaria().setDescricao("Bebês (zero a 1 ano e 6 meses)");
		aprendizagemDesenvolvimento.getCampoExperiencia().setDescricao("Traços, sons, cores e formas");
		aprendizagemDesenvolvimento.setDescricao("Explorar sons produzidos com o próprio corpo e com objetos do ambiente.");


		aprendizagemDesenvolvimento.setCodigo("EI01" + "TS02");
		aprendizagemDesenvolvimento.getFaixaEtaria().setCodigo("EI01");
		aprendizagemDesenvolvimento.getCampoExperiencia().setCodigo("TS01");
		aprendizagemDesenvolvimento.getFaixaEtaria().setDescricao("Bebês (zero a 1 ano e 6 meses)");
		aprendizagemDesenvolvimento.getCampoExperiencia().setDescricao("O eu, o outro e o nós");
		aprendizagemDesenvolvimento.setDescricao("Explorar sons produzidos com o próprio corpo e com objetos do ambiente.");
		
		
//		aprendizagemDesenvolvimento.setCodigo(faixaEtaria.getCodigo() + campoExperiencia.getCodigo());
//		aprendizagemDesenvolvimento.setDescricao("Explorar sons produzidos com o próprio corpo e com objetos do ambiente");

//		aprendizagemDesenvolvimento.setCodigo(faixaEtaria.getCodigo() + campoExperiencia.getCodigo());
//		aprendizagemDesenvolvimento.setDescricao("Criar sons com materiais, objetos e instrumentos musicais, para acompanhar diversos ritmos de música");
//
//		aprendizagemDesenvolvimento.setCodigo(faixaEtaria.getCodigo() + campoExperiencia.getCodigo());

		repository.save(aprendizagemDesenvolvimento);

		aprendizagemDesenvolvimentoList = repository.findAll();
		System.out.println(aprendizagemDesenvolvimentoList);
	}

}
