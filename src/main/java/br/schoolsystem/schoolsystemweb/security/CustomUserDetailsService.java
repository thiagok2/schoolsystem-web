package br.schoolsystem.schoolsystemweb.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.schoolsystem.schoolsystemweb.repositories.UsuarioRepository;
import br.schoolsystem.schoolsystemweb.security.model.Usuario;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(userName).orElseThrow(
								() -> new UsernameNotFoundException("Email " + userName +" não encontrado.")
							);
		
		return new User(usuario.getEmail(), usuario.getPassword(), getAuthorities(usuario));
	}

	
	private static Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario){
		String[] permissoesUsuario = usuario.getPermissoes().stream()
				.map((permissao) -> permissao.getNome()).toArray(String[]::new);
		
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissoesUsuario);
		
		return authorities;
	}
}
