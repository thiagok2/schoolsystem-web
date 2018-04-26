package br.schoolsystem.schoolsystemweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.model.Disciplina;

public interface DisciplinaRepository 
	extends JpaRepository<Disciplina, Integer>{

}
