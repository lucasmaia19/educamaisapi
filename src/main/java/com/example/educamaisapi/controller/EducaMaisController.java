package com.example.educamaisapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.repository.AtividadeRepository;
import com.example.educamaisapi.service.AtividadeService;

@CrossOrigin
@RestController
@RequestMapping("api")
public class EducaMaisController {
	
	@Autowired
	private AtividadeRepository educaMaisRepository;
	
	@Autowired
	private AtividadeService atividadeService;
	
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
	
	@PostMapping("/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

		String message;
		
		atividadeService.store(file);
		message = "Enviado arquivo";
	
//	if (atividadeService.store(file) != null) {
//	} else {
//		message = "Falha ao enviar arquivo";
//	}

	}
}
