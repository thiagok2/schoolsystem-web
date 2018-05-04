package br.schoolsystem.schoolsystemweb.resources.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Disciplina;
import br.schoolsystem.schoolsystemweb.model.Nota;
import br.schoolsystem.schoolsystemweb.repositories.AlunoRepository;
import br.schoolsystem.schoolsystemweb.repositories.DisciplinaRepository;
import br.schoolsystem.schoolsystemweb.repositories.custom.BoletimRepository;

@RestController
@RequestMapping("/api/boletim")
public class BoletimResource {

	@Autowired
	BoletimRepository boletimRepository;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@RequestMapping(value="{alunoId}/notas")
	public List<Nota> notasAluno(
			@PathVariable("alunoId") Integer alunoId, 
			@RequestParam(value="disciplinaId",defaultValue="0") Integer disciplinaId){
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		
		if(disciplinaId==0)
			return boletimRepository.getNotas(aluno);
		else {
			Disciplina disciplina = disciplinaRepository.getOne(disciplinaId);
			
			return boletimRepository.getNotas(aluno, disciplina);
		}
			
	}
	
}
