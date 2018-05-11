package br.schoolsystem.schoolsystemweb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  dataNascimento;
	
	@Column
	private char sexo;

	@Column
	private String matricula;
	
	@Column
	private Boolean fluxoNormal = true;

	@ManyToMany
	@JoinTable(name = "aluno_disciplinas",
	joinColumns = @JoinColumn(name = "aluno_id"),
	inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@ElementCollection
	private List<String> telefones = new ArrayList<String>();

	@Column(name = "aluno_Enum", nullable = true)
	@Enumerated(EnumType.STRING)
	private TipoDeAluno tipoDeAluno = TipoDeAluno.BOLSAASSISTENCIA;
	
	@ManyToOne
	private Curso curso;
	
	@Embedded
	private Endereco endereco = new Endereco();
	

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


	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getFluxoNormal() {
		return fluxoNormal;
	}

	public void setFluxoNormal(Boolean fluxoNormal) {
		this.fluxoNormal = fluxoNormal;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void addDisciplina(Disciplina disciplina) {
		if(!disciplinas.contains(disciplina)) {
			//disciplina.addAluno(this);
			disciplinas.add(disciplina);
		}
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", matricula=" + matricula
				+ ", disciplinas=" + disciplinas + ", telefones=" + telefones + ", tipoDeAluno=" + tipoDeAluno
				+ ", endereco=" + endereco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + idade;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + sexo;
		result = prime * result + ((telefones == null) ? 0 : telefones.hashCode());
		result = prime * result + ((tipoDeAluno == null) ? 0 : tipoDeAluno.hashCode());
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
