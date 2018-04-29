package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Pessoa;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	List<Pessoa> findByNomeLike(String nome);
	
	
	@Query("from Aluno")
	List<Aluno> listAluno();
}
