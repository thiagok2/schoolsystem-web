package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	List<Aluno> findByNomeLike(String nome);

}
