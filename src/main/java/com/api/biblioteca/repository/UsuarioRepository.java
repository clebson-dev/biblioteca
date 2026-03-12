package com.api.biblioteca.repository;

import com.api.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByUsuarioAndAtivoTrue(String usuario);

    List<Usuario> findAllByAtivoTrue();

    Optional<Usuario> findByIdAndAtivoTrue(Integer id);
}