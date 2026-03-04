package com.api.biblioteca.repository;

import com.api.biblioteca.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // O JpaRepository já traz métodos como save(), findById(), findAll() e deleteById()
}
