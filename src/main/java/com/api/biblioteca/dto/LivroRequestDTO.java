package com.api.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class LivroRequestDTO {

    @NotBlank(message = "O título do livro é obrigatório.")
    @Size(max = 100, message = "O título não pode exceder 100 caracteres.")
    private String titulo;

    private Integer anoPublicacao;

    @Size(max = 45, message = "O ISBN não pode exceder 45 caracteres.")
    private String isbn;

    // Recebemos apenas os IDs das chaves estrangeiras
    @NotNull(message = "A categoria é obrigatória.")
    private Integer idCategoria;

    @NotNull(message = "A editora é obrigatória.")
    private Integer idEditora;

    @NotEmpty(message = "O livro deve ter pelo menos um autor.")
    private List<Integer> idAutores;
}
