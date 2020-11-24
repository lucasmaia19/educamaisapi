package com.example.educamaisapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public Atividade store(MultipartFile file) throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Atividade atividade = new Atividade(file.getBytes());
		atividade.setNomeArquivo(fileName);

		return atividadeRepository.save(atividade);
	}
}
