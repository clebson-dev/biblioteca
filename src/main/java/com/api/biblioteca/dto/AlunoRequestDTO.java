package com.api.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoRequestDTO {
    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(max = 11)
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 11)
    private String telefone;

    @Size(max = 50)
    private String email;

    @NotBlank(message = "O CEP é obrigatório.")
    @Size(max = 8)
    private String cep;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(max = 45)
    private String estado;

    @NotBlank(message = "A cidade é obrigatória.")
    @Size(max = 45)
    private String cidade;

    @NotBlank(message = "O endereço é obrigatório.")
    @Size(max = 100)
    private String endereco;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(max = 45)
    private String bairro;
}
