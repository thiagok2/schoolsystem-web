package br.schoolsystem.schoolsystemweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

}
