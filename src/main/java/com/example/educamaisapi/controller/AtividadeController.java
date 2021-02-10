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
import com.example.educamaisapi.model.AprendizagemDesenvolvimento;
import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.repository.AprendizagemDesenvolvimentoRepository;
import com.example.educamaisapi.repository.AtividadeRepository;
import com.example.educamaisapi.repository.CampoExperienciaRepository;
import com.example.educamaisapi.repository.FaixaEtariaRepository;
import com.example.educamaisapi.service.AtividadeService;
import com.example.educamaisapi.util.ReportUtil;

//@CrossOrigin(origins = "https://educa-mais-ui.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("atividade")
public class AtividadeController {

	@Autowired
	private AtividadeRepository atividadeRepository;

	@Autowired
	private FaixaEtariaRepository faixaEtariaRepository;

	@Autowired
	private CampoExperienciaRepository campoExperienciaRepository;

	@Autowired
	private AprendizagemDesenvolvimentoRepository aprendizagemDesenvolvimentoRepository;

	@Autowired
	private AtividadeService atividadeService;

	@Autowired
	private HttpServletResponse response;

	@GetMapping
	public List<Atividade> listaCadastros() {
		return atividadeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Atividade> listaUmCadastro(@PathVariable Long id) { 
		return atividadeRepository.findById(id);
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
		atividadeRepository.deleteById(id);

		Map<String, Object> responseMap = new HashMap<>();
		return responseMap;
	}
	
	@PutMapping("/{id}")
	public Atividade atualizarCadastro(@RequestBody Atividade atividade, @PathVariable Long id) {
		
		Atividade atividadeSaved = atividadeRepository.findById(id).get();

		BeanUtils.copyProperties(atividade, atividadeSaved, "id");

		return atividadeRepository.save(atividadeSaved);
	}

	@PostMapping("/upload-com-dados")
	public ResponseEntity<Atividade> uploadComDados(@ModelAttribute AtividadeDTO atividadeDTO, 
			@RequestPart(required = false) String faixaEtariaOp, @RequestPart(required = false) String campoExperienciaOp,
			@RequestPart(required = false) String aprendizagemDesenvolvimentoOp) throws IOException {
		
		if (faixaEtariaOp != null) {

			faixaEtariaOp = faixaEtariaOp.replaceAll("[\\\\\"]", "");
			List<String> idListFaixaEtaria = Stream.of(faixaEtariaOp.split(",", -1)).collect(Collectors.toList());
	
			List<FaixaEtaria> faixaEtariaList = new ArrayList<>();
			if (faixaEtariaList != null) {
				for (String id : idListFaixaEtaria) {
					FaixaEtaria faixaEtariaSaved = faixaEtariaRepository.findById(Long.valueOf(id)).get();
					faixaEtariaList.add(faixaEtariaSaved);
				}
			}
			
			atividadeDTO.setFaixaEtariaList(faixaEtariaList);
		}

		if (faixaEtariaOp != null) {

			campoExperienciaOp = campoExperienciaOp.replaceAll("[\\\\\"]", "");
			List<String> idListCampoExperiencia = Stream.of(campoExperienciaOp.split(",", -1)).collect(Collectors.toList());

			List<CampoExperiencia> campoExperienciaList = new ArrayList<>();
			for (String id : idListCampoExperiencia) {
				CampoExperiencia campoExperienciaSaved = campoExperienciaRepository.findById(Long.valueOf(id)).get();
				campoExperienciaList.add(campoExperienciaSaved);
			}

			atividadeDTO.setCampoExperienciaList(campoExperienciaList);
		}
		
		if (faixaEtariaOp != null) {

			aprendizagemDesenvolvimentoOp = aprendizagemDesenvolvimentoOp.replaceAll("[\\\\\"]", "");
			List<String> idListAprendizagemDesenvolvimento = Stream.of(aprendizagemDesenvolvimentoOp.split(",", -1)).collect(Collectors.toList());
			
			List<AprendizagemDesenvolvimento> aprendizagemDesenvolvimentoList = new ArrayList<>();;
			for (String id : idListAprendizagemDesenvolvimento) {
				AprendizagemDesenvolvimento aprendizagemDesenvolvimentoSaved = aprendizagemDesenvolvimentoRepository.findById(Long.valueOf(id)).get();
				aprendizagemDesenvolvimentoList.add(aprendizagemDesenvolvimentoSaved);
			}

			atividadeDTO.setAprendizagemDesenvolvimentoList(aprendizagemDesenvolvimentoList);
		}

		return ResponseEntity.ok(atividadeService.uploadComDados(atividadeDTO));
	}

}
