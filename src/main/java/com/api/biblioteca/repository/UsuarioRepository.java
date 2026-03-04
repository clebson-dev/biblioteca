package com.api.biblioteca.repository;

import com.api.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Método obrigatório para o Spring Security encontrar o utilizador pelo login
    Optional<Usuario> findByUsuario(String usuario);

}