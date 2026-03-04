package com.api.biblioteca.config;

import com.api.biblioteca.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // Lê a chave secreta e o tempo de expiração do application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    // Transforma a string do application.properties numa chave criptográfica
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Método que gera o Token
    public String gerarToken(Usuario usuario) {
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Biblioteca") // Quem gerou o token
                .setSubject(usuario.getUsuario()) // A quem pertence o token
                .setIssuedAt(hoje) // Data de criação
                .setExpiration(dataExpiracao) // Data de expiração
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Algoritmo de assinatura
                .compact();
    }

    // Método que verifica se o Token é válido e extrai o "username"
    public String getSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}