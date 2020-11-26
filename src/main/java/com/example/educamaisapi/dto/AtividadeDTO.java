package com.example.educamaisapi.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AtividadeDTO {

	private String nome;

	private String tag;

	private MultipartFile arquivo;

}
