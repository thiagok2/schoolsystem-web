package br.schoolsystem.schoolsystemweb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.schoolsystem.schoolsystemweb.security.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);
}
