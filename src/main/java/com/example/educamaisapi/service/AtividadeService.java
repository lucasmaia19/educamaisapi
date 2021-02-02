package com.example.educamaisapi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.educamaisapi.dto.AtividadeDTO;
import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.model.FaixaEtaria;
import com.example.educamaisapi.model.Teste2;
import com.example.educamaisapi.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;

	public Atividade uploadComDados(AtividadeDTO atividadeDTO, List<FaixaEtaria> faixaEtariaList) throws IOException {
		Atividade atividade = new Atividade();

		atividade.setNome(atividadeDTO.getNome());
		atividade.setEnunciado(atividadeDTO.getEnunciado());
//		atividade.setFaixaEtaria(atividadeDTO.getFaixaEtaria());
//		atividade.setCampoExperiencia(atividadeDTO.getCampoExperiencia());
//		atividade.setAprendizagemDesenvolvimento(atividadeDTO.getAprendizagemDesenvolvimento());
		atividade.setFaixaEtariaList(atividadeDTO.getFaixaEtariaList());
		atividade.setCampoExperienciaList(atividadeDTO.getCampoExperienciaList());
		atividade.setAprendizagemDesenvolvimentoList(atividadeDTO.getAprendizagemDesenvolvimentoList());
		
		if (atividadeDTO.getArquivo() != null) {
			atividade.setArquivo(atividadeDTO.getArquivo().getBytes());
			atividade.setArquivoNome(atividadeDTO.getArquivo().getOriginalFilename());
			atividade.setArquivoExtensao(atividadeDTO.getArquivo().getContentType());
			atividade.setArquivoTamanho(atividadeDTO.getArquivo().getSize());
		}

		return atividadeRepository.save(atividade);
	}
}
