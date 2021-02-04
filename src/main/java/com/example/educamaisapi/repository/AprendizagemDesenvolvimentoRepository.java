package com.example.educamaisapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;
import com.example.educamaisapi.model.CampoExperiencia;
import com.example.educamaisapi.model.FaixaEtaria;

@Repository
public interface AprendizagemDesenvolvimentoRepository extends JpaRepository<AprendizagemDesenvolvimento, Long> {

	public List<AprendizagemDesenvolvimento> findAllByCampoExperienciaIn(List<CampoExperiencia> campoExperiencia);
	
	public List<AprendizagemDesenvolvimento> findAllByFaixaEtariaIn(List<FaixaEtaria> faixaEtaria);
	
	public List<AprendizagemDesenvolvimento> findAllByFaixaEtariaInAndCampoExperienciaIn(List<FaixaEtaria> faixaEtaria, List<CampoExperiencia> campoExperiencia);

}
