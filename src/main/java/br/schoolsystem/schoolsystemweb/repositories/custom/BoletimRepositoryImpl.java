package br.schoolsystem.schoolsystemweb.repositories.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Disciplina;
import br.schoolsystem.schoolsystemweb.model.DisciplinaNota;
import br.schoolsystem.schoolsystemweb.model.Nota;

@Repository
public class BoletimRepositoryImpl implements BoletimRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional(readOnly=true)
	public List<Nota> getNotas(Aluno aluno) {
		TypedQuery<Nota> query = entityManager.createQuery("SELECT n FROM Nota n "
				+ "INNER JOIN n.aluno a WHERE a.id = :alunoId", Nota.class);
		
		query.setParameter("alunoId", aluno.getId());
		return query.getResultList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Nota> getNotas(Aluno aluno, Disciplina disciplina) {
		TypedQuery<Nota> query = entityManager.createQuery("SELECT n FROM Nota n "
				+ "INNER JOIN n.aluno a "
				+ "INNER JOIN n.disciplina d "
				+ "WHERE a.id = :alunoId and d.id = :disciplinaId", Nota.class);
		
		query.setParameter("alunoId", aluno.getId());
		query.setParameter("disciplinaId", disciplina.getId());
		return query.getResultList();
	}

	@Override
	public List<DisciplinaNota> getDisciplinaNota(Aluno aluno) {
		String querySQL = "SELECT new br.schoolsystem.schoolsystemweb.model.DisciplinaNota(n.disciplina.nome, n.mediaFinal) "
				+ "FROM Nota n "
				+ ""
				+ "WHERE n.aluno.id = :alunoId"; 
		
		TypedQuery<DisciplinaNota> query = entityManager.createQuery(querySQL, DisciplinaNota.class);
		
		query.setParameter("alunoId", aluno.getId());
		return query.getResultList();
	}

}
