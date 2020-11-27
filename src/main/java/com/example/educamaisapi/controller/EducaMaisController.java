package com.example.educamaisapi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.educamaisapi.dto.AtividadeDTO;
import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.repository.AtividadeRepository;
import com.example.educamaisapi.service.AtividadeService;
import com.example.educamaisapi.util.ReportUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("atividade")
public class EducaMaisController {

	@Autowired
	private AtividadeRepository educaMaisRepository;

	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private HttpServletResponse response;

	@GetMapping
	public List<Atividade> listaCadastros() {
		return educaMaisRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Atividade> listaUmCadastro(@PathVariable Long id) { 
		return educaMaisRepository.findById(id);
	}
	
	@PostMapping("/upload-com-dados")
	public ResponseEntity<Atividade> uploadComDados(@ModelAttribute AtividadeDTO atividadeDTO) throws IOException {
		return ResponseEntity.ok(atividadeService.uploadComDados(atividadeDTO));
	}
	
	@GetMapping("/gerar-pdf/{id}")
	public void gerarPDF(@PathVariable Long id) throws Exception {

		System.out.println(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
//		ReportUtil.reportMakePdf("report/atividade-pdf.jrxml", params);
		ReportUtil.reportMakePdf("report/atividade-pdf.jrxml", params, response);
	}

}
