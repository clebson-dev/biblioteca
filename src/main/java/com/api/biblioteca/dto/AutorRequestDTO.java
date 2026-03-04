package com.api.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutorRequestDTO {
    @NotBlank(message = "O nome do autor é obrigatório.")
    @Size(max = 100)
    private String autor;

    @Size(max = 45)
    private String pseudonimo;

    @Size(max = 45)
    private String nacionalidade;

    @Size(max = 255)
    private String enderecoWeb;

    @Size(max = 50)
    private String email;

    @Size(max = 14)
    private String telefone;
}