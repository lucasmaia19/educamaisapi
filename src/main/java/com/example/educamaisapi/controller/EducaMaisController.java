package com.example.educamaisapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
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
import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.model.Cabecalho;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.model.Teste2;
import com.example.educamaisapi.model.dto.MultSelectDTO;
import com.example.educamaisapi.repository.AtividadeRepository;
import com.example.educamaisapi.repository.CabecalhoRepository;
import com.example.educamaisapi.repository.CampoExperienciaRepository;
import com.example.educamaisapi.repository.FaixaEtariaRepository;
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
	private FaixaEtariaRepository faixaEtariaRepository;

	@Autowired
	private CampoExperienciaRepository campoExperienciaRepository;
	
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
	public ResponseEntity<Atividade> uploadComDados(@ModelAttribute AtividadeDTO atividadeDTO, 
			@RequestPart String faixaEtariaOp, @RequestPart String campoExperienciaOp) throws IOException {
		
		// Remove caracteres do inicio e fim da string.
		faixaEtariaOp = faixaEtariaOp.replaceAll("\\[|\\]", "");
		
		// Quebra string em lista.
		List<String> idListFaixaEtaria = Stream.of(faixaEtariaOp.split(",", -1)).collect(Collectors.toList());
		
		List<FaixaEtaria> faixaEtariaList = new ArrayList<>();
		
		for (String id : idListFaixaEtaria) {
			FaixaEtaria faixaEtariaSaved = faixaEtariaRepository.findById(Long.valueOf(id)).get();
			faixaEtariaList.add(faixaEtariaSaved);
		}

		campoExperienciaOp = campoExperienciaOp.replaceAll("\\[|\\]", "");
		List<String> idListCampoExperiencia = Stream.of(campoExperienciaOp.split(",", -1)).collect(Collectors.toList());
		
		List<CampoExperiencia> campoExperienciaList = new ArrayList<>();
		
		for (String id : idListCampoExperiencia) {
			CampoExperiencia campoExperienciaSaved = campoExperienciaRepository.findById(Long.valueOf(id)).get();
			campoExperienciaList.add(campoExperienciaSaved);
		}
		
		
		atividadeDTO.setFaixaEtariaList(faixaEtariaList);
		
		atividadeDTO.setCampoExperienciaList(campoExperienciaList);
		
		return ResponseEntity.ok(atividadeService.uploadComDados(atividadeDTO, faixaEtariaList));
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

	@PostMapping("/teste")
	public ResponseEntity<Teste2> teste(@RequestPart String opcoes) throws IOException {

		ObjectMapper mapper = new ObjectMapper(); 
		Teste2 teste2 = mapper.readValue(opcoes, Teste2.class);
//
//		String resposta = "";
//		for (MultSelectDTO item : teste2.getNome()) {
//			System.out.println(item);
//			resposta += item + "; ";
//		}
//	
		return ResponseEntity.ok(teste2);
	}

}
