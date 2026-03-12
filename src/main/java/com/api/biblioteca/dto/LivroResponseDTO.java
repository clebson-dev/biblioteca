package com.api.biblioteca.dto;

import lombok.Data;
import java.util.List;

@Data
public class LivroResponseDTO {

    private Integer id;
    private String titulo;
    private Integer anoPublicacao;
    private String isbn;

    private CategoriaResponseDTO categoria;
    private EditoraResponseDTO editora;
    private List<AutorResponseDTO> autores;
}
