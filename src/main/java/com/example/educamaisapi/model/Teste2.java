package com.example.educamaisapi.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
class MultSelect {

	private String id;

	private String nome;
}

@Getter @Setter
public class Teste2 {

	private List<MultSelect> multSelect;

}
