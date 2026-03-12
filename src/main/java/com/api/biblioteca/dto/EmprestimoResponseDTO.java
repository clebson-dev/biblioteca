package com.api.biblioteca.dto;

import com.api.biblioteca.model.enums.StatusEmprestimo;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmprestimoResponseDTO {
    private Integer id;
    private String nomeUsuario;
    private String nomeAluno;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private List<ItemEmprestimoDTO> itens;

    @Data
    public static class ItemEmprestimoDTO {
        private Integer idLivro;
        private String tituloLivro;
        private LocalDate dataDevolucao;
        private StatusEmprestimo status;
    }
}