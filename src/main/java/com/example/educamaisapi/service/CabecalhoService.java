package com.example.educamaisapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.educamaisapi.dto.CabecalhoDTO;
import com.example.educamaisapi.model.Cabecalho;
import com.example.educamaisapi.repository.CabecalhoRepository;

@Service
public class CabecalhoService {

	@Autowired
	private CabecalhoRepository cabecalhoRepository;

	public Cabecalho uploadComDadosCabecalho(CabecalhoDTO cabecalhoDTO) throws IOException {
		Cabecalho cabecalho = new Cabecalho();

		cabecalho.setProfessora(cabecalhoDTO.getProfessora());
		cabecalho.setTurma(cabecalhoDTO.getTurma());
		cabecalho.setData(cabecalhoDTO.getData());
		cabecalho.setAluno(cabecalhoDTO.getAluno());
		cabecalho.setNomeEscola(cabecalhoDTO.getNomeEscola());
		cabecalho.setLogradouro(cabecalhoDTO.getLogradouro());
		cabecalho.setTel(cabecalhoDTO.getTel());
		cabecalho.setCep(cabecalhoDTO.getCep());
		cabecalho.setEmail(cabecalhoDTO.getEmail());

		if (cabecalhoDTO.getLogoPrefeitura() != null) {
			cabecalho.setLogoPrefeitura(cabecalhoDTO.getLogoPrefeitura().getBytes());
		}

		if (cabecalhoDTO.getLogoEscola() != null) {
			cabecalho.setLogoEscola(cabecalhoDTO.getLogoEscola().getBytes());
		}

		return cabecalhoRepository.save(cabecalho);
	}
}
