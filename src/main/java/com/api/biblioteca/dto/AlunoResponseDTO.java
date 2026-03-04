package com.api.biblioteca.dto;

import lombok.Data;

@Data
public class AlunoResponseDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String cep;
    private String estado;
    private String cidade;
    private String endereco;
    private String bairro;
}
