package com.api.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parametro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TINYTEXT")
    private String parametro;

    @Column(nullable = false)
    private Float valor;
}
