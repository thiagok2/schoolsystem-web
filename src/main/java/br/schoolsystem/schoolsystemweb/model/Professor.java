package br.schoolsystem.schoolsystemweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.schoolsystem.schoolsystemweb.model.enums.TipoProfessor;

@Entity
public class Professor{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String nome;
	
	@Column
	private int idade;
	
	@Column
	private char sexo;
	
	@Column
	private String formacao;
	
	@OneToMany
	@JsonIgnore
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@Column(name = "professor_Enum", nullable = true)
	@Enumerated(EnumType.STRING)
	private TipoProfessor tipoProfessor = TipoProfessor.EFETIVO;
		
	public Professor() {
		

		super();
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public TipoProfessor getTipoProfessor() {
		return tipoProfessor;
	}

	public void setTipoProfessor(TipoProfessor tipoProfessor) {
		this.tipoProfessor = tipoProfessor;
	}

	public String getFormacao() {
		return formacao;
	}
	
	public void setFormacao(String formacao) {
		if(formacao != null) {
			this.formacao = formacao;
		}
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
		int result = super.hashCode();
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
		result = prime * result + ((formacao == null) ? 0 : formacao.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (disciplinas == null) {
			if (other.disciplinas != null)
				return false;
		} else if (!disciplinas.equals(other.disciplinas))
			return false;
		if (formacao == null) {
			if (other.formacao != null)
				return false;
		} else if (!formacao.equals(other.formacao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Professor [formacao=" + formacao + ", disciplinas=" + disciplinas + "]";
	}

}