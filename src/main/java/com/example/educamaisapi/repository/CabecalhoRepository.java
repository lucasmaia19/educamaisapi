package com.example.educamaisapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.educamaisapi.model.Atividade;
import com.example.educamaisapi.model.Cabecalho;

@Repository
public interface CabecalhoRepository extends JpaRepository<Cabecalho, Long> {

}
