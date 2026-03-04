package com.api.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String autor;

    @Column(length = 45)
    private String pseudonimo;

    @Column(length = 45)
    private String nacionalidade;

    @Column(name = "endereco_web", length = 255)
    private String enderecoWeb;

    @Column(length = 50)
    private String email;

    @Column(length = 14)
    private String telefone;
}
