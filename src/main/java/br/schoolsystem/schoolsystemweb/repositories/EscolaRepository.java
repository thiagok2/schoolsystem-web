package br.schoolsystem.schoolsystemweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {

}
