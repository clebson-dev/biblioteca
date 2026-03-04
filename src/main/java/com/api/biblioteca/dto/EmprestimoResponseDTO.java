package com.api.biblioteca.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmprestimoResponseDTO {
    private Integer id;
    private String nomeUsuario; // Nome do bibliotecário
    private String nomeAluno;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private List<ItemEmprestimoDTO> itens;

    @Data
    public static class ItemEmprestimoDTO {
        private Integer idLivro;
        private String tituloLivro;
        private LocalDate dataDevolucao;
        private String status; // Ex: "DEVOLVIDO", "PENDENTE", "ATRASADO"
    }
}