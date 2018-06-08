package br.schoolsystem.schoolsystemweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.schoolsystem.schoolsystemweb.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	List<Aluno> findByNomeLike(String nome);
	
	Aluno findByEmail(String email);
	
	
	@Query("SELECT a FROM Aluno a "
			+ "INNER JOIN a.disciplinas d "
			+ "INNER JOIN d.curso c "
			+ "WHERE c.id = :cursoId")
	List<Aluno> searchAlunoByCursoId(@Param("cursoId") Integer cursoId);

}
