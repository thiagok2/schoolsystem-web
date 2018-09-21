package br.schoolsystem.schoolsystemweb.resources.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.schoolsystem.schoolsystemweb.model.Curso;
import br.schoolsystem.schoolsystemweb.repositories.CursoRepository;

@RestController
@RequestMapping("/api/curso")
public class CursoResource {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	
	@GetMapping("/{id}")
	public Curso buscar(@PathVariable Integer id){
		return cursoRepository.findById(id).get();
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar(Pageable pageable){
		return new ResponseEntity<>(cursoRepository.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/pesquisar")
	public List<Curso> pesquisar(@RequestParam("nome") String nome){
		List<Curso> cursos = cursoRepository.findByNomeContainingIgnoreCase(nome);
		
		return cursos;
	}
	
	
	
	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		cursoRepository.deleteById(id);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Curso> salvar(@RequestBody Curso curso){
		Curso cursoOk = cursoRepository.save(curso);
		
		ResponseEntity<Curso> response = new ResponseEntity<>(cursoOk, HttpStatus.CREATED);
		
		return response;
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<?> atualizar(@RequestBody Curso curso){
		cursoRepository.save(curso);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
