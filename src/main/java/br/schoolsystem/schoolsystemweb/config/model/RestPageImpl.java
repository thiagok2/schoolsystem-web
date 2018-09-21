package br.schoolsystem.schoolsystemweb.config.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class RestPageImpl <T> extends PageImpl<T>{

	private static final long serialVersionUID = 3248189030448292002L;

	  public RestPageImpl(List<T> content, Pageable pageable, long total) {
	    super(content, pageable, total);
	    // TODO Auto-generated constructor stub
	  }

	  public RestPageImpl(List<T> content) {
	    super(content);
	    // TODO Auto-generated constructor stub
	  }

	  /* PageImpl does not have an empty constructor and this was causing an issue for RestTemplate to cast the Rest API response
	   * back to Page.
	   */
	  public RestPageImpl() {
	    super(new ArrayList<T>());
	  }
}
