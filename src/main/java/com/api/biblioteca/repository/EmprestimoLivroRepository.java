package com.api.biblioteca.repository;

import com.api.biblioteca.model.EmprestimoLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, Integer> {

    // Método personalizado do Spring Data JPA para encontrar um livro específico de um empréstimo
    Optional<EmprestimoLivro> findByEmprestimoIdAndLivroId(Integer idEmprestimo, Integer idLivro);

    // Método para listar todos os itens de um empréstimo
    List<EmprestimoLivro> findByEmprestimoId(Integer idEmprestimo);
}