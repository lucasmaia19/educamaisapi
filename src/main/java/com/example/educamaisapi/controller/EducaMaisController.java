package com.example.educamaisapi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.model.Cabecalho;
import com.example.educamaisapi.model.Teste2;
import com.example.educamaisapi.model.dto.MultSelectDTO;
import com.example.educamaisapi.repository.AtividadeRepository;
import com.example.educamaisapi.repository.CabecalhoRepository;
import com.example.educamaisapi.service.AtividadeService;
import com.example.educamaisapi.service.CabecalhoService;
import com.example.educamaisapi.util.ReportUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

//@CrossOrigin(origins = "https://educa-mais-ui.herokuapp.com")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("atividade")
public class EducaMaisController {

	@Autowired
	private AtividadeRepository educaMaisRepository;

	@Autowired
	private CabecalhoRepository cabecalhoRepository;

	@Autowired
	private AtividadeService atividadeService;

	@Autowired
	private CabecalhoService cabecalhoService;

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

	@GetMapping("/gerar-pdf/{id_atividade}/{id_cabecalho}")
	public void gerarPDF(@PathVariable Long id_atividade, @PathVariable Long id_cabecalho) throws Exception {

		System.out.println(id_atividade);
		System.out.println(id_cabecalho);

		Map<String, Object> params = new HashMap<>();
		params.put("atividade_id", id_atividade);
		params.put("cabecalho_id", id_cabecalho);

		System.out.println(params);

		ReportUtil.reportMakePdf("report/atividade-pdf.jrxml", params, response);
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> deletarCadastro(@PathVariable Long id) {
		educaMaisRepository.deleteById(id);

		Map<String, Object> responseMap = new HashMap<>();
		return responseMap;
	}
	
	@PutMapping("/{id}")
	public Atividade atualizarCadastro(@RequestBody Atividade atividade, @PathVariable Long id) {
		
		Atividade atividadeSaved = educaMaisRepository.findById(id).get();
		
		BeanUtils.copyProperties(atividade, atividadeSaved, "id");
		
		return educaMaisRepository.save(atividadeSaved);
	}

	@PostMapping("/upload-com-dados")
	public ResponseEntity<Atividade> uploadComDados(@ModelAttribute AtividadeDTO atividadeDTO) throws IOException {
		return ResponseEntity.ok(atividadeService.uploadComDados(atividadeDTO));
	}

	@PostMapping("/upload-com-dados-cabecalho")
	public ResponseEntity<Cabecalho> uploadComDadosCabecalho(@ModelAttribute CabecalhoDTO cabecalhoDTO) throws IOException {
		
//		Instant dataAquisicaoInstant = Instant.parse(cabecalhoDTO.getData());
//		OffsetDateTime dataAquisicaoOffSet = dataAquisicaoInstant.atOffset(ZoneOffset.UTC); 
//
//		DateTimeFormatter data = DateTimeFormatter.ofPattern("ddMMyyyy");
//		String dataString = dataAquisicaoOffSet.format(data);
//
//		cabecalhoDTO.setData(dataString);

		return ResponseEntity.ok(cabecalhoService.uploadComDadosCabecalho(cabecalhoDTO));
	
	}

	@GetMapping("/cabecalho")
	public List<Cabecalho> listarCabecalho() {
		return cabecalhoRepository.findAll();
	}
	
	@DeleteMapping("cabecalho/{id}")
	public Map<String, Object> deletarCabecalho(@PathVariable Long id) {
		cabecalhoRepository.deleteById(id);

		Map<String, Object> responseMap = new HashMap<>();
		return responseMap;
	}

	@PutMapping("cabecalho/{id}")
	public Cabecalho atualizarCabecalho(@RequestBody Cabecalho cabecalho, @PathVariable Long id) {
		
		Cabecalho cabecalhoSaved = cabecalhoRepository.findById(id).get();
		
		BeanUtils.copyProperties(cabecalho, cabecalhoSaved, "id");

		return cabecalhoRepository.save(cabecalhoSaved);
	}
	
	@GetMapping("cabecalho/{id}")
	public java.util.Optional<Cabecalho> consultarIdCabecalho(@PathVariable Long id) {

	return cabecalhoRepository.findById(id);

	}

//	@PostMapping("/teste")
	@PostMapping(value = "/teste", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//	public Object teste(@ModelAttribute Teste2 teste2) throws IOException {
//	public Object teste(@ModelAttribute List<Teste2> teste2) throws IOException {
//	public Object teste(@RequestBody List<Teste2> teste2) throws IOException {
//	public Object teste(@ModelAttribute Teste2 teste2) throws IOException {
//	public Object teste(@ModelAttribute Object teste2) throws IOException {
//	public Object teste(@ModelAttribute Object teste2) throws IOException {
//	public Object teste(@RequestParam Teste2 teste2) throws IOException {
//	public Object teste(@RequestParam List<Teste2> teste2) throws IOException {
	
//	public Object teste(@RequestPart Teste2 opcoes) throws IOException {
//	public Object teste(@RequestPart String opcoes) throws IOException {  // Funciona
//	public Object teste(@ModelAttribute String opcoes) throws IOException {
//	public Object teste(@ModelAttribute Object opcoes) throws IOException {
//	public Object teste(@ModelAttribute(name = "opcoes") Teste2 opcoes) throws IOException {
	public ResponseEntity<Teste2> teste(@RequestPart String opcoes) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		Teste2 teste2 = mapper.readValue(opcoes, Teste2.class);

		String resposta = "";
		for (MultSelectDTO item : teste2.getNome()) {
			System.out.println(item);
			resposta += item + "; ";
		}
	
		return ResponseEntity.ok(teste2);
	}

}
