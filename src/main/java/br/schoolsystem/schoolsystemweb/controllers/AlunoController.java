package br.schoolsystem.schoolsystemweb.controllers;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.model.Disciplina;
import br.schoolsystem.schoolsystemweb.model.enums.TipoDeAluno;
import br.schoolsystem.schoolsystemweb.repositories.AlunoRepository;
import br.schoolsystem.schoolsystemweb.repositories.DisciplinaRepository;
import br.schoolsystem.schoolsystemweb.services.EnderecoService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@Autowired
	EnderecoService enderecoService;
	
	@RequestMapping(value= "/testError", method=RequestMethod.GET)
	public String testException(@RequestParam(required=false) Integer param) {
		if(param == null){
			throw new DataAccessException("Que merda! Deu erro") {};
		}
		
		return "aluno/new";
	}
	
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
	public String saveAluno(@Valid Aluno aluno, BindingResult result,
							ModelMap model,
							RedirectAttributes attributes) {
		
		System.out.println(aluno);
		
		if (result.hasErrors()) {
			return "aluno/form";
		}
		
		alunoRepository.saveAndFlush(aluno);
		
		model.addAttribute("mensagem", "Aluno " + aluno.getNome() + " registrado com sucesso");
		
		attributes.addFlashAttribute("msg", "flash attributes");
		
		
		return "redirect:/aluno/list";
	}
	
	@RequestMapping(value = { "/edit-{id}-aluno" }, method = RequestMethod.GET)
	public String editAluno(@PathVariable("id") Integer id, ModelMap model) {
		Aluno aluno = alunoRepository.getOne(id);
		model.addAttribute("aluno", aluno);
		model.addAttribute("edit", true);
		
		model.addAttribute("tiposAlunosAll", Arrays.asList(TipoDeAluno.ALL));
		model.addAttribute("municipioAll", enderecoService.getMunicipios());
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
	
	@PostMapping("/uploadFile")
	public String handleFileUpload(@RequestParam("anexo") MultipartFile file){
		if (!file.isEmpty()){
			String name = file.getOriginalFilename();
			try{
				byte[] bytes = file.getBytes();
				Files.write(new File(name).toPath(), bytes);
			}catch (Exception e){
			e.printStackTrace();
				}
		}
		return "redirect:/aluno/list";
	}
	
	
	
	
}
