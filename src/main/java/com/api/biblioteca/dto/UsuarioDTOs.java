package com.api.biblioteca.dto;

import com.api.biblioteca.model.enums.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UsuarioDTOs {

    @Data
    public static class Request {
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100)
        private String nome;

        @NotBlank(message = "O utilizador é obrigatório.")
        @Size(max = 100)
        private String usuario;

        @NotBlank(message = "A senha é obrigatória.")
        @Size(max = 100)
        private String senha;

        @NotNull(message = "O perfil de acesso é obrigatório.")
        private PerfilUsuario perfil;
    }

    @Data
    public static class Response {
        private Integer id;
        private String nome;
        private String usuario;
        private PerfilUsuario perfil;
        private Boolean ativo;

    }
}