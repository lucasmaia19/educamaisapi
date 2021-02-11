package com.example.educamaisapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.repository.AprendizagemDesenvolvimentoRepository;
import com.example.educamaisapi.repository.CampoExperienciaRepository;
import com.example.educamaisapi.repository.FaixaEtariaRepository;

//@CrossOrigin(origins = "https://educa-mais-ui.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("atividade")
public class ListagemBNCCController {

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
	
	@GetMapping("aprendizagem-desenvolvimento/filter/campo-experiencia-id-list")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoFilterCampoExperienciaIdList(@RequestParam List<Long> campoExperienciaIdList) {

		System.out.println(campoExperienciaIdList);

		List<CampoExperiencia> campoExperienciaList = new ArrayList<>();
		campoExperienciaIdList.forEach(id -> {
			campoExperienciaList.add(campoExperienciaRepository.findById(id).get());
		});
		System.out.println(campoExperienciaList);

		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList = aprendizagemDesenvolvimentoRepository.findAllByCampoExperienciaIn(campoExperienciaList);
		System.out.println(aprendizagemDesenvolvimentoList);
		
		return aprendizagemDesenvolvimentoList;

	}
	
	@GetMapping("aprendizagem-desenvolvimento/filter/faixa-etaria-id-list")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoFilterFaixaEtariaIdList(
			@RequestParam List<Long> faixaEtariaIdList) {

		List<FaixaEtaria> faixaEtariaList = new ArrayList<>();
		faixaEtariaIdList.forEach(id -> {
			faixaEtariaList.add(faixaEtariaRepository.findById(id).get());
		});
		
		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList = aprendizagemDesenvolvimentoRepository
				.findAllByFaixaEtariaIn(faixaEtariaList);

		return aprendizagemDesenvolvimentoList;
	}
	
	@GetMapping("aprendizagem-desenvolvimento/filter/ce-fe-id-list")
	public List<AprendizagemDesenvolvimento> listaAprendizagemDesenvolvimentoCeFeIdList(
			@RequestParam List<Long> faixaEtariaIdList, 
			@RequestParam List<Long> campoExperienciaIdList) {
		
		
		System.out.println("faixaEtariaIdList" + faixaEtariaIdList);
		System.out.println("campoExperienciaIdList" + campoExperienciaIdList);
		
		List<CampoExperiencia> campoExperienciaList = new ArrayList<>();
		campoExperienciaIdList.forEach(id -> {
			campoExperienciaList.add(campoExperienciaRepository.findById(id).get());
		});
		
		List<FaixaEtaria> faixaEtariaList = new ArrayList<>();
		faixaEtariaIdList.forEach(id -> {
			faixaEtariaList.add(faixaEtariaRepository.findById(id).get());
		});
		
		List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoListCeFe = aprendizagemDesenvolvimentoRepository
				.findAllByFaixaEtariaInAndCampoExperienciaIn(faixaEtariaList, campoExperienciaList);

		return aprendizagemDesenvolvimentoListCeFe;
	}
	
}
