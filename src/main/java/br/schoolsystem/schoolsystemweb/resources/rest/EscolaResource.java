package br.schoolsystem.schoolsystemweb.resources.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.schoolsystem.schoolsystemweb.model.Escola;
import br.schoolsystem.schoolsystemweb.repositories.EscolaRepository;

@RestController
@RequestMapping("/api/escola")
public class EscolaResource {
	
	@Autowired
	EscolaRepository escolaRepository;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public Escola buscar(@PathVariable("id") Integer id) {
		return escolaRepository.getOne(id);
	}
	
	@RequestMapping(value="listar", method=RequestMethod.GET)
	public List<Escola> listar(){
		return escolaRepository.findAll();
	}
	
	
	@RequestMapping(value="pesquisar", method=RequestMethod.GET)
	public List<Escola> pesquisar(
			@RequestParam(name="nome", defaultValue="ALL")String nome){
		return escolaRepository.findByNome(nome);
		
	}

}
