package com.example.educamaisapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.educamaisapi.dto.AtividadeDTO;
import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.repository.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepository;

	public Atividade uploadComDados(AtividadeDTO atividadeDTO) throws IOException {
		Atividade atividade = new Atividade();
		
		atividade.setNome(atividadeDTO.getNome());
		atividade.setTag(atividadeDTO.getTag());
		
		if (atividadeDTO.getArquivo() != null) {
			atividade.setArquivo(atividadeDTO.getArquivo().getBytes());
			atividade.setArquivoNome(atividadeDTO.getArquivo().getOriginalFilename());
			atividade.setArquivoExtensao(atividadeDTO.getArquivo().getContentType());
			atividade.setArquivoTamanho(atividadeDTO.getArquivo().getSize());
		}
		
		return atividadeRepository.save(atividade);
	}
	
//	public Atividade store(MultipartFile file) throws IOException {
//
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//		Atividade atividade = new Atividade(file.getBytes());
//		atividade.setNomeArquivo(fileName);
//
//		return atividadeRepository.save(atividade);
//	}
}
