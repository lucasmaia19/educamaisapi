package com.example.educamaisapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.repository.EducaMaisRepository;


@RestController
@RequestMapping("api")
public class EducaMaisController {
	
	@Autowired
	EducaMaisRepository educaMaisRepository;
	
	@GetMapping
	public List<Atividade> listaCadastros() {
		return educaMaisRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Atividade> listaUmCadastro(@PathVariable Long id) { 
		return educaMaisRepository.findById(id);
	}
	
	@PostMapping
	public Atividade cadastrar(@RequestBody Atividade atividade) {
		return educaMaisRepository.save(atividade);
	}

}
