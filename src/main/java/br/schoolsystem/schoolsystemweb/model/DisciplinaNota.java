package br.schoolsystem.schoolsystemweb.model;

public class DisciplinaNota {
	
	private String disciplina;
	private Double nota;
	
	public DisciplinaNota(String disciplina, Double nota) {
		this.disciplina = disciplina;
		this.nota = nota;
	}
	
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
}
