package com.api.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false, length = 45)
    private String estado;

    @Column(nullable = false, length = 45)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, length = 45)
    private String bairro;
}
