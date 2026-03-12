package com.api.biblioteca.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEmprestimo {

    PENDENTE("Pendente"),
    DEVOLVIDO("Devolvido"),
    DEVOLVIDO_COM_ATRASO("Devolvido com Atraso"),
    EM_ATRASO("Em Atraso");

    private final String descricao;

    StatusEmprestimo(String descricao) {
        this.descricao = descricao;
    }


    @JsonValue
    public String getDescricao() {
        return descricao;
    }
}