package com.api.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "multa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emprestimo_livro", nullable = false, unique = true)
    private EmprestimoLivro emprestimoLivro;

    @Column(nullable = false)
    private Float valor;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;
}
