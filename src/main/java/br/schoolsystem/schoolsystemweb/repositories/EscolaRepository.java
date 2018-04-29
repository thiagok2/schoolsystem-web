package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {
	
	public List<Escola> findByNome(String nome);

}
