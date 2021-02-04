package com.example.educamaisapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.educamaisapi.dto.AtividadeDTO;
import com.example.educamaisapi.dto.CabecalhoDTO;
import com.example.educamaisapi.model.AprendizagemDesenvolvimento;
import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.model.Cabecalho;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.repository.AprendizagemDesenvolvimentoRepository;
import com.example.educamaisapi.repository.AtividadeRepository;
import com.example.educamaisapi.repository.CabecalhoRepository;
import com.example.educamaisapi.repository.CampoExperienciaRepository;
import com.example.educamaisapi.repository.FaixaEtariaRepository;
import com.example.educamaisapi.service.AtividadeService;
import com.example.educamaisapi.service.CabecalhoService;
import com.example.educamaisapi.util.ReportUtil;

@CrossOrigin(origins = "https://educa-mais-ui.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("cabecalho")
public class CabecalhoController {

	@Autowired
	private CabecalhoRepository cabecalhoRepository;

	@Autowired
	private CabecalhoService cabecalhoService;

	@PostMapping("/upload-com-dados-cabecalho")
	public ResponseEntity<Cabecalho> uploadComDadosCabecalho(@ModelAttribute CabecalhoDTO cabecalhoDTO) throws IOException {
		
		return ResponseEntity.ok(cabecalhoService.uploadComDadosCabecalho(cabecalhoDTO));
	}

	@GetMapping("")
	public List<Cabecalho> listarCabecalho() {
		return cabecalhoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public java.util.Optional<Cabecalho> consultarIdCabecalho(@PathVariable Long id) {
		
		return cabecalhoRepository.findById(id);
	}
	
	@DeleteMapping("{id}")
	public Map<String, Object> deletarCabecalho(@PathVariable Long id) {
		cabecalhoRepository.deleteById(id);

		Map<String, Object> responseMap = new HashMap<>();
		return responseMap;
	}

	@PutMapping("{id}")
	public Cabecalho atualizarCabecalho(@RequestBody Cabecalho cabecalho, @PathVariable Long id) {
		
		Cabecalho cabecalhoSaved = cabecalhoRepository.findById(id).get();
		
		BeanUtils.copyProperties(cabecalho, cabecalhoSaved, "id");

		return cabecalhoRepository.save(cabecalhoSaved);
	}
	

}
