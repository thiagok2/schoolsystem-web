package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.schoolsystem.schoolsystemweb.model.Disciplina;

public interface DisciplinaRepository 
	extends JpaRepository<Disciplina, Integer>{
	
	public List<Disciplina> findByPeriodo(Integer periodo);
	
	@Query("SELECT nome FROM Disciplina WHERE periodo = :pPeriodo")
	public List<String> findNomeByPeriodo(@Param("pPeriodo") Integer periodo);
	
	

}
