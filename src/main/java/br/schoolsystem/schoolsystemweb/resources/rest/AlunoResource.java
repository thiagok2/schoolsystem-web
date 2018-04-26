package br.schoolsystem.schoolsystemweb.resources.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Pessoa;
import br.schoolsystem.schoolsystemweb.model.enums.TipoDeAluno;
import br.schoolsystem.schoolsystemweb.repositories.PessoaRepository;

@RestController
@RequestMapping("/aluno")
public class AlunoResource {
	
	@Autowired
	private PessoaRepository alunoRepositoty;
	
	@GetMapping("/iniciar")
	public String iniciar(){
		
		
		Aluno aluno = new Aluno("0002");
		aluno.setIdade(19);
		aluno.setNome("Bruna");
		aluno.setSexo('F');
		aluno.setTipoDeAluno(TipoDeAluno.BOLSAASSISTENCIA);
		
		alunoRepositoty.save(aluno);
		
		return "";
		
	}
	
	@GetMapping("/{id}")
	public Aluno buscar(@PathVariable Integer id) {
		Pessoa p =  alunoRepositoty.findById(id).get();
		
		if(p instanceof Aluno)
			return (Aluno)p;
		else return null;
	}
	   
	@GetMapping("/listar")
	public List<Aluno> listar() {
		
		//return alunoRepositoty.listAluno();
		
		System.out.println("list alunos");
		return alunoRepositoty.findAll().stream().filter(p -> p instanceof Aluno)
				.map(Aluno.class::cast)
				.collect(Collectors.toList());
		
	}
  
 
	@GetMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		alunoRepositoty.deleteById(id);
	}

}
