package com.example.educamaisapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;

@Repository
public interface AprendizagemDesenvolvimentoRepository extends JpaRepository<AprendizagemDesenvolvimento, Long> {

	@Query(value = "select * from aprendizagem_desenvolvimento where faixa_etaria_id = :faixa_etaria_id", nativeQuery = true)
	public List<AprendizagemDesenvolvimento> listByFaixaEtariaId(@Param("faixa_etaria_id") Long faixaEtariaId);

	@Query(value = "select * from aprendizagem_desenvolvimento ad "
			+ "join faixa_etaria fe on ad.faixa_etaria_id = fe.id "
			+ "where fe.codigo = :codigo", nativeQuery = true)
	public List<AprendizagemDesenvolvimento> listByFaixaEtariaCodigo(@Param("codigo") String codigo);

	@Query(value = "select * from aprendizagem_desenvolvimento where campo_experiencia_id = :campo_experiencia_id", nativeQuery = true)
	public List<AprendizagemDesenvolvimento> listByCampoExperienciaId(@Param("campo_experiencia_id") Long campoExperienciaId);
	
	@Query(value = "select * from aprendizagem_desenvolvimento ad"
			+ " join campo_experiencia ce on ad.campo_experiencia_id = ce.id"
			+ " where ce.codigo = :codigo", nativeQuery = true)
	public List<AprendizagemDesenvolvimento> listByCampoExperienciaCodigo(@Param("codigo") String codigo);
}
