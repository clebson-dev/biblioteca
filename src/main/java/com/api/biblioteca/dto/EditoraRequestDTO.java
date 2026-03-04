package com.api.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditoraRequestDTO {
    @NotBlank(message = "O nome da editora é obrigatório.")
    @Size(max = 45)
    private String editora;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Size(max = 45)
    private String cnpj;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Size(max = 45)
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 14)
    private String telefone;

    @Size(max = 45)
    private String cep;

    @Size(max = 20)
    private String estado;

    @Size(max = 45)
    private String cidade;

    @Size(max = 45)
    private String bairro;

    @Size(max = 100)
    private String endereco;

    @Size(max = 45)
    private String nacionalidade;

    @Size(max = 100)
    private String enderecoWeb;
}