package br.schoolsystem.schoolsystemweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.schoolsystem.schoolsystemweb.model.enums.TipoDeAluno;

@Entity
public class Aluno{
	
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
	private String matricula;

	@ManyToMany
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@ElementCollection
	private List<String> telefones = new ArrayList<String>();

	@Column(name = "aluno_Enum", nullable = true)
	@Enumerated(EnumType.STRING)
	private TipoDeAluno tipoDeAluno = TipoDeAluno.BOLSAASSISTENCIA;
	
	@Embedded
	private Endereco endereco;
	

	public Aluno(String matricula) {
		super();
		this.matricula = matricula;
	}

	public Aluno() {
		super();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public TipoDeAluno getTipoDeAluno() {
		return tipoDeAluno;
	}

	public void setTipoDeAluno(TipoDeAluno tipoDeAluno) {
		this.tipoDeAluno = tipoDeAluno;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (disciplinas == null) {
			if (other.disciplinas != null)
				return false;
		} else if (!disciplinas.equals(other.disciplinas))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", disciplinas=" + disciplinas + "]";
	}

	public void addDisciplina(Disciplina disciplina) {
		if(!disciplinas.contains(disciplina))
			disciplinas.add(disciplina);
	}
	
	

}
