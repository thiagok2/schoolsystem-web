package br.schoolsystem.schoolsystemweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.schoolsystem.schoolsystemweb.model.Curso;
import br.schoolsystem.schoolsystemweb.repositories.CursoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolsystemWebApplicationTests {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Test
	public void contextLoads() {
		Curso c = new Curso(5, "PRONATEC - Informática Básica");
		
		cursoRepository.save(c);
		
	}

}
