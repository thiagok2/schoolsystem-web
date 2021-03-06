package br.schoolsystem.schoolsystemweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	throws Exception{
		auth
		.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/resources/","/static/**", "/css/**", "/js/**", "/images/**", "/webjars/","/assets/")
        		.permitAll()
        	.antMatchers("/").permitAll()
        	.antMatchers("/admin/").hasRole("ADMIN")
        	.antMatchers("/api").permitAll()
            .anyRequest().permitAll()//.authenticated() //
            .and()
//            .formLogin()
//	            .loginPage("/login")
//	            .defaultSuccessUrl("/home")
//	            .failureUrl("/login?error")
//	            .permitAll()
//	            .and()
	        .logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/login?logout")
		        .permitAll()
		        .and()
		    .exceptionHandling()
		        .accessDeniedPage("/accessDenied")
	           	
            .and().csrf().disable();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
