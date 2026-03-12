package com.api.biblioteca.repository;

import com.api.biblioteca.model.EmprestimoLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, Integer> {

    Optional<EmprestimoLivro> findByEmprestimoIdAndLivroId(Integer idEmprestimo, Integer idLivro);

    List<EmprestimoLivro> findByEmprestimoId(Integer idEmprestimo);
}