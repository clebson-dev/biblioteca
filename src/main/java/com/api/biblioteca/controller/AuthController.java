package com.api.biblioteca.controller;

import com.api.biblioteca.config.TokenService;
import com.api.biblioteca.model.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @Data
    public static class LoginDTO {
        @NotBlank private String usuario;
        @NotBlank private String senha;
    }

    @Data
    public static class TokenDTO {
        private String token;
        private String tipo = "Bearer";
        public TokenDTO(String token) { this.token = token; }
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> efetuarLogin(@RequestBody @Valid LoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getUsuario(), dados.getSenha());

        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}