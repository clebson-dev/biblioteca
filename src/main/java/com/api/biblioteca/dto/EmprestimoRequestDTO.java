package com.api.biblioteca.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmprestimoRequestDTO {

    @NotNull(message = "O ID do utilizador (bibliotecário) é obrigatório.")
    private Integer idUsuario;

    @NotNull(message = "O ID do aluno é obrigatório.")
    private Integer idAluno;

    @NotNull(message = "A data de devolução prevista é obrigatória.")
    @Future(message = "A data de devolução prevista tem de ser no futuro.")
    private LocalDate dataDevolucaoPrevista;

    @NotEmpty(message = "Deve incluir pelo menos um livro no empréstimo.")
    private List<Integer> idLivros;
}