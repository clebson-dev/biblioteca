package com.api.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "editora")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String editora;

    @Column(nullable = false, length = 45)
    private String cnpj;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 14)
    private String telefone;

    @Column(length = 45)
    private String cep;

    @Column(length = 20)
    private String estado;

    @Column(length = 45)
    private String cidade;

    @Column(length = 45)
    private String bairro;

    @Column(length = 100)
    private String endereco;

    @Column(length = 45)
    private String nacionalidade;

    @Column(name = "endereco_web", length = 100)
    private String enderecoWeb;
}
