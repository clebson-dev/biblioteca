package com.api.biblioteca.dto;

import lombok.Data;

@Data
public class CategoriaResponseDTO {

    // O que queremos devolver ao Front-end
    private Integer id;
    private String categoria;
    private String descricao;
}
