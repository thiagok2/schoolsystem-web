package br.schoolsystem.schoolsystemweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="curso")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Column
	private String nome;
	
	@JsonIgnore
	@OneToMany
	private List<Aluno> alunos;
	
	@JsonIgnore
	@OneToMany
	private List<Disciplina> disciplinas;
	
	
	public Curso() {
		super();
	}

	public Curso(Integer id, String nome, List<Aluno> alunos, List<Disciplina> disciplinas) {
		super();
		this.id = id;
		this.nome = nome;
		this.alunos = alunos;
		this.disciplinas = disciplinas;
	}

	public Curso(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	/*public void setId(int id) {
		this.id = id;
	}*/

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

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Curso other = (Curso) obj;
		if (alunos == null) {
			if (other.alunos != null)
				return false;
		} else if (!alunos.equals(other.alunos))
			return false;
		if (disciplinas == null) {
			if (other.disciplinas != null)
				return false;
		} else if (!disciplinas.equals(other.disciplinas))
			return false;
		if (id != other.id)
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
		return "Curso: id = " + id + ", nome = " + nome + ", alunos = " + alunos + ", disciplinas = " + disciplinas + ".";
	}
	
	
	
	
	
	
	
}
