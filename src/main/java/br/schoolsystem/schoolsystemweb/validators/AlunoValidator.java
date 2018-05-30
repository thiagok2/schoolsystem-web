package br.schoolsystem.schoolsystemweb.validators;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.schoolsystem.schoolsystemweb.model.Aluno;

public class AlunoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Aluno.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Aluno aluno = (Aluno)object;
		
		LocalDate date = aluno.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matricula", "field.required");
		
		/*
		if(aluno.getTelefones() == null || aluno.getTelefones().isEmpty())
			errors.rejectValue("telefones", "field.required");
		*/
	}

}
