package com.api.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequestDTO {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 50, message = "O nome da categoria não pode exceder 50 caracteres.")
    private String categoria;

    @Size(max = 200, message = "A descrição não pode exceder 200 caracteres.")
    private String descricao;
}
