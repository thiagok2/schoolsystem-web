package br.schoolsystem.schoolsystemweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.schoolsystem.schoolsystemweb.model.Aluno;
import br.schoolsystem.schoolsystemweb.repositories.AlunoRepository;

@Service
@Transactional(readOnly=true)
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;
	
	/**
	 * Sort
	 * @return
	 */
	public List<Aluno> findAlunoAll(){
		
		Sort sortData = new Sort(Direction.ASC, "dataNascimento");
		return alunoRepository.findAll(sortData);
	}
	
	/**
	 * Paginação
	 * @param pagina
	 * @param limite
	 * @return
	 */
	public List<Aluno> findAllPagened(Integer pagina, Integer limite){
		Pageable pageable = PageRequest.of(pagina, limite);
		Page<Aluno> alunosPage = alunoRepository.findAll(pageable);
		
		return alunosPage.getContent();
	}
}
