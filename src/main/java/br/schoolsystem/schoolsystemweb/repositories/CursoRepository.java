package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
	/*SPRING DATA*/
	List<Curso> findByNomeContaining(String nome);

}
