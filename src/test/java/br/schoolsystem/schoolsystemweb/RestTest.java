package br.schoolsystem.schoolsystemweb;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Curso;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTest {
	private static final String ROOT_URL = "http://localhost:8080";
	RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testGetAllAlunos(){
		ResponseEntity<Aluno[]> responseEntity = restTemplate.getForEntity(ROOT_URL+"/api/aluno/listar", Aluno[].class);
		List<Aluno> alunos = Arrays.asList(responseEntity.getBody());
		assertNotNull(alunos);
	}
	
	@Test
	public void testCreateCurso(){
		Curso curso = new Curso();
		curso.setNome("Geografia");
		
		ResponseEntity<Curso> postResponse = restTemplate.postForEntity(ROOT_URL+"/api/curso/salvar", curso, Curso.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
}
