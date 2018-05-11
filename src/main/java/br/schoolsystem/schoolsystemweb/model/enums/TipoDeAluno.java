package br.schoolsystem.schoolsystemweb.model.enums;

public enum TipoDeAluno {
	BOLSAASSISTENCIA("Bolsista da Assistência Estudantil"), 
	BOLSISTADEPESQUISA("Bolsista de Projeto de Pesquisa"),
	BOLSISTADEEXTENSÃO("Bolsista de Projeto de Extensão"),
	SEMBOLSA("Sem Bolsa");
	
	private final String nome;
	
	public static final TipoDeAluno[] ALL = {BOLSAASSISTENCIA, BOLSISTADEPESQUISA,BOLSISTADEEXTENSÃO,SEMBOLSA};

	
	TipoDeAluno(final String  nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
