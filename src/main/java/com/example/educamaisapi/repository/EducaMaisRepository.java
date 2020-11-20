package com.example.educamaisapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.educamaisapi.model.Atividade;

@Repository
public interface EducaMaisRepository extends JpaRepository<Atividade, Long> {

}
