package br.schoolsystem.schoolsystemweb.resources.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.schoolsystem.schoolsystemweb.model.Professor;
import br.schoolsystem.schoolsystemweb.repositories.ProfessorRepository;

@RestController
@RequestMapping("/api/professor")
public class ProfessorResource {
	
	@Autowired
	ProfessorRepository professorRepository;

	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public Professor buscar(@PathVariable("id") Integer id) {
		return professorRepository.getOne(id);
	}
	
	@RequestMapping(value="listar", method=RequestMethod.GET)
	public List<Professor> listar(){
		return professorRepository.findAll();
	}
}
