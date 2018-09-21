package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.schoolsystem.schoolsystemweb.model.Curso;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Integer> {
	
	/*SPRING DATA*/
	List<Curso> findByNomeContainingIgnoreCase(String nome);
	
	

}
