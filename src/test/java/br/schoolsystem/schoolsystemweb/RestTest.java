package br.schoolsystem.schoolsystemweb;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.schoolsystem.schoolsystemweb.config.model.RestPageImpl;
import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Curso;

//@RunWith(SpringRunner.class)
//@SpringBootTest
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
		curso.setId(40);
		curso.setNome("Geografia Inglesa");

		ResponseEntity<Curso> postResponse = restTemplate.postForEntity(ROOT_URL+"/api/curso/salvar", curso, Curso.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}



	@Test
	public void listAlunoAll() {
		ResponseEntity<List<Aluno>> exchange = restTemplate.exchange(ROOT_URL+"/api/aluno/listar", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aluno>>() {
		});

		System.out.println(exchange.getBody());
	}

	//@Test
	public void testCursoList(){


		ParameterizedTypeReference<RestPageImpl<Curso>> responseType = new ParameterizedTypeReference<RestPageImpl<Curso>>() { };

		ResponseEntity<RestPageImpl<Curso>> result = restTemplate.exchange(ROOT_URL+"/api/curso/listar", HttpMethod.GET, null/*httpEntity*/, responseType);

		List<Curso> searchResult = result.getBody().getContent();

		System.out.println("cursos::");
		System.out.println(searchResult);
	}
	
	@Test
	public void saveCursoExchageSecurity() {
		
		Curso curso = new Curso();
		curso.setNome("Eng. Agrícola");
		
		RestTemplate restTemplateAdmin = new RestTemplateBuilder()
				.rootUri("http://localhost:8080/api/curso/salvar")
				.basicAuthorization("admin@gmail.com", "admin")
				.build();

		ResponseEntity<Curso> exchangePost = restTemplateAdmin.exchange(ROOT_URL+"/api/curso/salvar",
				HttpMethod.POST, new HttpEntity<>(curso, createJSONHeader()), Curso.class);
		
		System.out.println(exchangePost.getBody());
	}
	@Test
	public void deleteCurso(){
		RestTemplate restTemplateAdmin = new RestTemplateBuilder()
				//.rootUri("http://localhost:8080/api/curso/salvar")
				.basicAuthorization("admin@gmail.com", "admin")
				.build();
		
		restTemplateAdmin.delete(ROOT_URL+"/api/curso/deletar/{id}", 43);
	}
	@Test
	public void updateCurso(){
		
		Curso curso = new Curso();
		curso.setId(43);
		curso.setNome("Cultura Inglesa");
		RestTemplate restTemplateAdmin = new RestTemplateBuilder()
				//.rootUri("http://localhost:8080/api/curso/salvar")
				.basicAuthorization("admin@gmail.com", "admin")
				.build();
		
		restTemplateAdmin.put(ROOT_URL+"/api/curso/atualizar", curso);
	}

	private static HttpHeaders createJSONHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
