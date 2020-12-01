package com.example.educamaisapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.educamaisapi.model.AprendizagemDesenvolvimento;

@Repository
public interface AprendizagemDesenvolvimentoRepository extends JpaRepository<AprendizagemDesenvolvimento, Long> {

}
