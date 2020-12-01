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

		aprendizagemDesenvolvimentoList = repository.findAll();


		FaixaEtaria faixaEtaria = new FaixaEtaria();
		faixaEtaria.setCodigo("EI01");
		faixaEtaria.setDescricao("Bebês (zero a 1 ano e 6 meses)");

		CampoExperiencia campoExperiencia = new CampoExperiencia();
		campoExperiencia.setCodigo("TS01");
		campoExperiencia.setDescricao("Traços, sons, cores e formas");

		AprendizagemDesenvolvimento aprendizagemDesenvolvimento = new AprendizagemDesenvolvimento();
		aprendizagemDesenvolvimento.setFaixaEtaria(faixaEtaria);
		aprendizagemDesenvolvimento.setCampoExperiencia(campoExperiencia);
		aprendizagemDesenvolvimento.setCodigo(faixaEtaria.getCodigo() + campoExperiencia.getCodigo());
		aprendizagemDesenvolvimento.setDescricao("Explorar sons produzidos com o próprio corpo e com objetos do ambiente");

		repository.save(aprendizagemDesenvolvimento);


		aprendizagemDesenvolvimentoList = repository.findAll();
		System.out.println(aprendizagemDesenvolvimentoList);
	}

}
