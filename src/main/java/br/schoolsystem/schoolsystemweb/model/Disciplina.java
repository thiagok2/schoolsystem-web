package br.schoolsystem.schoolsystemweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="disciplina")
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String nome;
	
	@Column
	private Integer periodo;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "aluno_disciplinas",
	joinColumns = @JoinColumn(name = "disciplina_id"),
	inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@ManyToOne
	private Professor professor;
	
	@ManyToOne
	private Curso curso;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Assunto> assuntos = new ArrayList<Assunto>();
	

	public Disciplina() {
		
	}
	
	public void addAluno(Aluno a){
		if(!alunos.contains(a))
			alunos.add(a);
	}
	
	public void addAlunos(List<Aluno> alunos){
		this.alunos.addAll(alunos);
		
		alunos.stream().forEach(a -> a.addDisciplina(this));
	}
	
	public void addAssunto(String assunto){
		Assunto a = new Assunto();
		a.setNome(assunto);
		a.setDisciplina(this);
		
		assuntos.add(a);
	}

	public Disciplina(Integer id, String nome, List<Aluno> alunos, Professor professor) {
		super();
		this.id = id;
		this.nome = nome;
		this.alunos = alunos;
		this.professor = professor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((assuntos == null) ? 0 : assuntos.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (alunos == null) {
			if (other.alunos != null)
				return false;
		}if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina: id = " + id + ", nome = " + nome + ", alunos = " + alunos + ", professor = " + professor + ".";
	}

	
	
	

}
