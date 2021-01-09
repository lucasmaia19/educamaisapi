package com.example.educamaisapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.repository.AprendizagemDesenvolvimentoRepository;
import com.example.educamaisapi.repository.CampoExperienciaRepository;
import com.example.educamaisapi.repository.FaixaEtariaRepository;

@CrossOrigin(origins = "https://educa-mais-ui.herokuapp.com/atividade")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("atividade")
public class TiposAtividadesController {

	@Autowired
	private AprendizagemDesenvolvimentoRepository aprendizagemDesenvolvimentoRepository;

	@Autowired
	private FaixaEtariaRepository faixaEtariaRepository;

	@Autowired
	private CampoExperienciaRepository campoExperienciaRepository;


	@GetMapping("faixa-etaria")
	public List<FaixaEtaria> listaFaixaEtaria() {
		return faixaEtariaRepository.findAll();
	}

	@GetMapping("campo-experiencia")
	public List<CampoExperiencia> listaCampoExperiencia() {
		return campoExperienciaRepository.findAll();
	}

	@GetMapping("aprendizagem-desenvolvimento")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimento() {
		return aprendizagemDesenvolvimentoRepository.findAll();
	}

	@GetMapping("aprendizagem-desenvolvimento/filter/faixa-etaria-id/{faixaEtariaId}")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoFilterFaixaEtariaId(@PathVariable Long faixaEtariaId) {

		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList = aprendizagemDesenvolvimentoRepository.listByFaixaEtariaId(faixaEtariaId);

		return aprendizagemDesenvolvimentoList;
	}

	@GetMapping("aprendizagem-desenvolvimento/filter/faixa-etaria-codigo/{faixaEtariaCodigo}")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoFilterFaixaEtariaCodigo(@PathVariable String faixaEtariaCodigo) {

		System.out.println(faixaEtariaCodigo);

		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList = aprendizagemDesenvolvimentoRepository.listByFaixaEtariaCodigo(faixaEtariaCodigo);

		return aprendizagemDesenvolvimentoList;
	}

	@GetMapping("aprendizagem-desenvolvimento/filter/campo-experiencia-id/{campoExperienciaId}")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoFilterCampoExperienciaId(@PathVariable Long campoExperienciaId) {

		System.out.println(campoExperienciaId);

		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList = aprendizagemDesenvolvimentoRepository.listByCampoExperienciaId(campoExperienciaId);

		return aprendizagemDesenvolvimentoList;
	}

	@GetMapping("aprendizagem-desenvolvimento/filter/campo-experiencia-codigo/{campoExperienciaCodigo}")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoCampoExperienciaCodigo(@PathVariable String campoExperienciaCodigo) {

		System.out.println(campoExperienciaCodigo);

		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentosList = aprendizagemDesenvolvimentoRepository.listByCampoExperienciaCodigo(campoExperienciaCodigo);

		return aprendizagemDesenvolvimentosList;
	}
	
	@GetMapping("aprendizagem-desenvolvimento/filter/ce-fe-id/{campoExperienciaId}/{faixaEtariaId}")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoCeFeId(@PathVariable Long campoExperienciaId, 
			@PathVariable Long faixaEtariaId) {
		
		System.out.println(campoExperienciaId);
		System.out.println(faixaEtariaId);
		
		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentosList = aprendizagemDesenvolvimentoRepository.listByCeAndFeId(campoExperienciaId, faixaEtariaId);
		
		return aprendizagemDesenvolvimentosList;
	}
	
	

}
