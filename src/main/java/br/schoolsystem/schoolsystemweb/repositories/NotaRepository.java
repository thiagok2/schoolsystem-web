package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Disciplina;
import br.schoolsystem.schoolsystemweb.model.Nota;

/**
 * 
 * @author SPRING DATA methods
 *
 */

public interface NotaRepository extends JpaRepository<Nota, Integer>{
	
	public List<Nota> findByAlunoAndDisciplina(Aluno a, Disciplina d);
	
	public List<Nota> findByAluno(Aluno a);
	
	
}
