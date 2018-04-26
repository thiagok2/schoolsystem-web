package br.schoolsystem.schoolsystemweb.repositories.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Nota;

public class BoletimRepositoryImpl implements BoletimRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Nota> getNotas(Aluno aluno) {
		
		return null;
	}

}
