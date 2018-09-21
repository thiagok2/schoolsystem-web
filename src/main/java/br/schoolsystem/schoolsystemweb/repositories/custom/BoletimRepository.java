package br.schoolsystem.schoolsystemweb.repositories.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Disciplina;
import br.schoolsystem.schoolsystemweb.model.DisciplinaNota;
import br.schoolsystem.schoolsystemweb.model.Nota;
@Repository
public interface BoletimRepository {
	
	public List<Nota> getNotas(Aluno aluno);
	
	public List<Nota> getNotas(Aluno aluno, Disciplina disciplina);
	
	public List<DisciplinaNota> getDisciplinaNota(Aluno aluno);

}
