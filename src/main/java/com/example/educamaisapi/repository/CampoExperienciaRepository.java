package com.example.educamaisapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.educamaisapi.model.CampoExperiencia;

@Repository
public interface CampoExperienciaRepository extends JpaRepository<CampoExperiencia, Long> {

}
