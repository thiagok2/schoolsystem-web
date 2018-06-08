package br.schoolsystem.schoolsystemweb.resources.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.repositories.AlunoRepository;

@RestController
@RequestMapping("/api/aluno")
public class AlunoResource {
	
	@Autowired
	private AlunoRepository alunoRepositoty;
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscar(@PathVariable Integer id) {
		Aluno aluno =  alunoRepositoty.findById(id).get();
		
		ResponseEntity<Aluno> response = new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
		return response;
	}
	   
	@GetMapping("/listar")
	public List<Aluno> listar() {
		return alunoRepositoty.findAll();
	}
  
 
	@GetMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		alunoRepositoty.deleteById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/salvar")
	public Aluno salvar(@RequestBody @Valid Aluno aluno, BindingResult results){
		Aluno alunoOk = alunoRepositoty.save(aluno);
		
		return alunoOk;
	}
	
	

}
