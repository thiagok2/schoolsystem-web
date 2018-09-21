package br.schoolsystem.schoolsystemweb.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.enums.TipoDeAluno;
import br.schoolsystem.schoolsystemweb.repositories.AlunoRepository;
import br.schoolsystem.schoolsystemweb.services.EnderecoService;

@Controller
@RequestMapping("/aluno2")
public class AlunoAjaxController {
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	EnderecoService enderecoService;
	
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newAluno(ModelMap model) {
		
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		
		model.addAttribute("tiposAlunosAll", Arrays.asList(TipoDeAluno.ALL));
		model.addAttribute("municipioAll", enderecoService.getMunicipios());
		
		return "aluno2/form";
	}

}
