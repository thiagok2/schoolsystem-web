package br.schoolsystem.schoolsystemweb.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Disciplina;
import br.schoolsystem.schoolsystemweb.model.enums.TipoDeAluno;
import br.schoolsystem.schoolsystemweb.repositories.AlunoRepository;
import br.schoolsystem.schoolsystemweb.repositories.DisciplinaRepository;
import br.schoolsystem.schoolsystemweb.sersvice.EnderecoService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@Autowired
	EnderecoService enderecoService;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listAluno(ModelMap model) {
		
		List<Aluno> alunos = alunoRepository.findAll();
		
		model.addAttribute("alunosList", alunos);
		
		model.addAttribute("message", "Lista de alunos");
		
		
		return "aluno/list";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newAluno(ModelMap model) {
		
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		model.addAttribute("edit", false);
		
		
		model.addAttribute("tiposAlunosAll", Arrays.asList(TipoDeAluno.ALL));
		
		model.addAttribute("municipioAll", enderecoService.getMunicipios());
		
		return "aluno/form";
	}
	
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public String saveAluno(@Valid @ModelAttribute Aluno aluno, BindingResult result,
							ModelMap model) {
		
		System.out.println(aluno);
		
		if (result.hasErrors()) {
			return "aluno/form";
		}
		
		alunoRepository.saveAndFlush(aluno);
		
		model.addAttribute("mensagem", "Aluno " + aluno.getNome() + " registrado com sucesso");
		
		return "redirect:/aluno/list";
	}
	
	@RequestMapping(value = { "/edit-{id}-aluno" }, method = RequestMethod.GET)
	public String editAluno(@PathVariable("id") Integer id, ModelMap model) {
		Aluno aluno = alunoRepository.getOne(id);
		model.addAttribute("aluno", aluno);
		model.addAttribute("edit", true);
		
		model.addAttribute("tiposAlunosAll", Arrays.asList(TipoDeAluno.ALL));
		return "aluno/form";
	}
	
	@RequestMapping(value = { "/edit-{id}-aluno" }, method = RequestMethod.POST)
	public String updateAluno(@Valid Aluno aluno, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "aluno/form";
		}
		
		alunoRepository.saveAndFlush(aluno);
		
		model.addAttribute("mensagem", "Aluno " + aluno.getNome() + " atualizado com sucesso");
		
		return "redirect:/aluno/list";
	}
	
	@RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
	public String deleteAluno(@RequestParam("alunoId") Integer id) {
		alunoRepository.deleteById(id);
		return "redirect:/aluno/list";
	}
	
	@RequestMapping(value = { "/aluno-disciplinas" }, method = RequestMethod.GET)
	public String alunoDisciplinas(@RequestParam("alunoId") Integer id, ModelMap model) {
		Aluno aluno = alunoRepository.getOne(id);
		
		List<Disciplina> disciplinasAll = disciplinaRepository.findAll();
		
	
		model.addAttribute("aluno", aluno);
		
		model.addAttribute("disciplinasAll", disciplinasAll);
		
		return "aluno/aluno-disciplinas";
	}
	
	
	@RequestMapping(value="/addDisciplina")
	public String addDisciplina(@RequestParam("disciplinaId") Integer disciplinaId,
			@RequestParam("alunoId") Integer alunoId) {
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		Disciplina disciplina =  disciplinaRepository.getOne(disciplinaId);
		
		aluno.addDisciplina(disciplina);
		
		alunoRepository.saveAndFlush(aluno);
		disciplinaRepository.saveAndFlush(disciplina);
		
		return "redirect:/aluno/aluno-disciplinas?alunoId="+aluno.getId();
	}
	
	@RequestMapping(value="/removeDisciplina")
	public String removeDisciplina(@RequestParam("disciplinaId") Integer disciplinaId,
			@RequestParam("alunoId") Integer alunoId) {
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		
		
		Boolean removed = aluno.getDisciplinas().removeIf(d -> d.getId().equals(disciplinaId));
		
		
		alunoRepository.saveAndFlush(aluno);
		
		return "redirect:/aluno/aluno-disciplinas?alunoId="+aluno.getId();
	}
	
	
	
	
	
}
