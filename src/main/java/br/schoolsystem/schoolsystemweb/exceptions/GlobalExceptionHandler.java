package br.schoolsystem.schoolsystemweb.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice para detalhar classes de exceptions
 * @author Thiago
 *
 */
//@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException(HttpServletRequest request, DataAccessException ex){
		logger.info("DataAccessException:: URL="+request.getRequestURL());
		return "error/db_error";
	}
	@ExceptionHandler(ServletRequestBindingException.class)
	public String servletRequestBindingException(ServletRequestBindingException e) {
		logger.error("ServletRequestBindingException: "+e.getMessage());
		return "error/validation_error";
	}
}
