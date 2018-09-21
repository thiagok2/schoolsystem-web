package br.schoolsystem.schoolsystemweb;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Disciplina;
import br.schoolsystem.schoolsystemweb.model.Endereco;
import br.schoolsystem.schoolsystemweb.model.Escola;
import br.schoolsystem.schoolsystemweb.model.Nota;
import br.schoolsystem.schoolsystemweb.model.Professor;
import br.schoolsystem.schoolsystemweb.model.enums.TipoDeAluno;
import br.schoolsystem.schoolsystemweb.model.enums.TipoProfessor;
import br.schoolsystem.schoolsystemweb.repositories.AlunoRepository;
import br.schoolsystem.schoolsystemweb.repositories.CursoRepository;
import br.schoolsystem.schoolsystemweb.repositories.DisciplinaRepository;
import br.schoolsystem.schoolsystemweb.repositories.EscolaRepository;
import br.schoolsystem.schoolsystemweb.repositories.NotaRepository;
import br.schoolsystem.schoolsystemweb.repositories.ProfessorRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@TestPropertySource(locations="classpath:test.properties")
public class SchoolsystemWebApplicationTests {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
//	@Autowired
//	private BoletimRepositoryImpl boletimRepository;
	
	@Test
	public void testAluno(){
		
		Aluno aluno = new Aluno("0069");
		aluno.setNome("Tayna2");
		aluno.setSexo('F');
		aluno.setTipoDeAluno(TipoDeAluno.BOLSAASSISTENCIA);
		
		aluno.getTelefones().add("99939-1223");
		
		
		Endereco e = new Endereco();
		e.setBairro("São Cristovão");
		e.setCep("57000-000");
		e.setEstado("CE");
		e.setCidade("Maranguape");
		e.setLogradouro("Rua da alegria");
		e.setNumero("SN");
		
		aluno.setEndereco(e);
		
		alunoRepository.save(aluno);
	}
	
	//@Test
	//@Transactional
	public void testCurso() {
		
		//Curso c = cursoRepository.getOne(2);
	
		//Aluno a = alunoRepository.getOne(1);
		
		
		//c.getAlunos().add(a);

		
		//cursoRepository.saveAndFlush(c);
	}
	
	//@Test
	//@Transactional
	public void testEscola() {
		Escola e = new Escola();
		
		e.setNome("IFAL - RL");
		
		//Curso c = cursoRepository.getOne(2);
		
		//e.getCursos().add(c);
		
		//escolaRepository.saveAndFlush(e);
		
		//Escola e = escolaRepository.findById(1).get();
		
	}
	
	
	public void testProfessor(){
		Professor p = new Professor();
		
		p.setFormacao("Ciência da Computação");
		
		p.setIdade(31);
		
		p.setSexo('M');
		
		p.setTipoProfessor(TipoProfessor.EFETIVO);
		
		p.setNome("Girafales");
		
		//professorRepository.saveAndFlush(p);
		
	}
	
	//@Test
	//@Transactional
	public void testEscolaProfessor() {
		Escola e = escolaRepository.findAll().get(0);
		
		Professor p = professorRepository.findAll().get(0);
		
		e.adicionarProfessor(p);
		
		//escolaRepository.saveAndFlush(e);
		
	}
	
	//@Test
	//@Transactional
	public void testDisciplina(){
		Disciplina d = new Disciplina();
		
		d.setNome("Programação WEB 1");
		
		List<Aluno> alunos = alunoRepository.findAll();
		
		d.addAlunos(alunos);
		
		Professor p = professorRepository.findAll().get(0);
		
		d.setProfessor(p);
		
		//disciplinaRepository.saveAndFlush(d);
		
	}
	
	//@Test
	//@Transactional
	public void testDisciplinaAssunto(){
		Disciplina d = disciplinaRepository.findAll().get(0);
		
		d.addAssunto("MVC");
		d.addAssunto("REST");
		d.addAssunto("Spring");
		d.addAssunto("JPA");
		
		Professor p =  professorRepository.findAll().get(0);
		
		p.getDisciplinas().add(d);
		d.setProfessor(p);
		
		//disciplinaRepository.saveAndFlush(d);
		//professorRepository.saveAndFlush(p);
	}
	
	//@Test
	//@Transactional
	public void testDisciplinaPeriodo(){
		List<String> disciplinas = disciplinaRepository.findNomeByPeriodo(1);
		
		//disciplinaRepository.saveAndFlush(d);
		//professorRepository.saveAndFlush(p);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>...."+disciplinas.size());
	}
	
	//@Test
	//@Transactional
	public void testDisciplinaAlunoNota(){
		Disciplina d = disciplinaRepository.findAll().get(0);
		
		Aluno a = alunoRepository.findAll().get(0);
		
		
		Nota nota = new Nota();
		nota.setAluno(a);
		nota.setDisciplina(d);
		
		nota.setMediaFinal(8d);
		
		nota.setNotas(Arrays.asList(10d,6d));
		
		//notaRepository.saveAndFlush(nota);
	}
	
	//@Test
	//@Transactional
	public void testNotas(){
		Disciplina d = disciplinaRepository.findAll().get(0);
		
		Aluno a = alunoRepository.findAll().get(0);
		
		
		
		
	}
	
	//@Test
	//@Transactional
	public void testDisciplinaNota(){
		
		Aluno a = alunoRepository.findAll().get(0);
		
		
		
	}

}
